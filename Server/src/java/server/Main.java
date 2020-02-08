/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.json.simple.JSONObject;
import operator.entity.DocumentRequest;

/**
 *
 * @author Bogdan
 */
public class Main {
    
    @Resource(lookup = "jms/__defaultConnectionFactory")
    public static ConnectionFactory connectionFactory;
    
    @Resource(lookup = "jmsQueue")
    public static javax.jms.Queue jmsQueue;

    public static final String PERSO_SUBMIT_URL = "http://collabnet.netset.rs:8081/is/persoCentar/submit";
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JMSContext context = connectionFactory.createContext();
        JMSConsumer consumer = context.createConsumer(jmsQueue);
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ServerPU");
        EntityManager em = emf.createEntityManager();
        
        System.out.println("Server started");
        
        while (true) {
            try {
                Message msg = consumer.receive();
                if (msg == null)
                    continue;
                
                if (msg instanceof ObjectMessage) {
                    ObjectMessage objMsg = (ObjectMessage) msg;
                    
                    DocumentRequest docReq = (DocumentRequest) objMsg.getObject();
                    
                    System.out.println(docReq.getJmbg());
                    
                    int id = ((DocumentRequest)em.createNamedQuery("DocumentRequest.findByJmbg")
                        .setParameter("jmbg", docReq.getJmbg()).getResultList().get(0)).getId();
                    
                    JSONObject obj = new JSONObject();
                    obj.put("id", "17011" + String.format("%07d", id));
                    obj.put("ime", docReq.getIme());
                    obj.put("prezime", docReq.getPrezime());
                    obj.put("imeMajke", docReq.getImeMajke());
                    obj.put("imeOca", docReq.getImeOca());
                    obj.put("prezimeMajke", docReq.getPrezimeMajke());
                    obj.put("prezimeOca", docReq.getPrezimeOca());
                    obj.put("pol", docReq.getPol());
                    obj.put("datumRodjenja", docReq.getDatumRodjenja());
                    obj.put("nacionalnost", docReq.getNacionalnost());
                    obj.put("profesija", docReq.getProfesija());
                    obj.put("bracnoStanje", docReq.getBracnoStanje());
                    obj.put("opstinaPrebivalista", docReq.getOpstinaPrebivalista());
                    obj.put("ulicaPrebivalista", docReq.getUlicaPrebivalista());
                    obj.put("brojPrebivalista", docReq.getBrojPrebivalista());
                    obj.put("JMBG", docReq.getJmbg());

                    System.out.println("Json: " + obj.toString());
                    
                    URL url = new URL(PERSO_SUBMIT_URL);
                    HttpURLConnection submitConnection = (HttpURLConnection) url.openConnection();
                    submitConnection.setRequestMethod("POST");
                    submitConnection.setRequestProperty("Content-Type", "application/json");
                    submitConnection.setDoOutput(true);
                    try (OutputStream os = submitConnection.getOutputStream()) {
                        os.write(obj.toString().getBytes());
                        os.flush();
                    }

                    if (submitConnection.getResponseCode() == 200){
                        docReq.setStatus("U produkciji");
                        em.getTransaction().begin();
                        // em.persist(docReq);
                        em.merge(docReq);
                        em.flush();
                        em.getTransaction().commit();
                        System.out.println("RESPONSE PERSO");
                    } else {
                        System.out.println("SUBMIT PERSO FAILED");
                    }
                    
                }
            } catch (JMSException | IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
