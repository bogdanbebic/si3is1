/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operator;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.simple.parser.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import operator.entity.DocumentRequest;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Bogdan
 */
public class PersoCentar {
    private static final String BASE_URL = "http://collabnet.netset.rs:8081/is/persoCentar/";
    
    public DocumentRequest getDocumentRequest(String id) {
        DocumentRequest docReq = null;
        try {
            HttpURLConnection httpConnection = (HttpURLConnection) new URL(BASE_URL + id).openConnection();
            if (httpConnection.getResponseCode() == 200) {
                JSONParser jsonParser = new JSONParser();
                JSONObject parsed = (JSONObject) jsonParser.parse(new InputStreamReader(httpConnection.getInputStream()));
                
                docReq = new DocumentRequest();
                docReq.setJmbg((String) parsed.get("JMBG"));
                docReq.setBracnoStanje((String) parsed.get("bracnoStanje"));
                docReq.setBrojPrebivalista((String) parsed.get("brojPrebivalista"));
                docReq.setDatumRodjenja((String) parsed.get("datumRodjenja"));
                docReq.setIme((String) parsed.get("ime"));
                docReq.setPrezime((String) parsed.get("prezime"));
                docReq.setImeMajke((String) parsed.get("imeMajke"));
                docReq.setPrezimeMajke((String) parsed.get("prezimeMajke"));
                docReq.setImeOca((String) parsed.get("imeOca"));
                docReq.setPrezimeOca((String) parsed.get("prezimeOca"));
                docReq.setNacionalnost((String) parsed.get("nacionalnost"));
                docReq.setOpstinaPrebivalista((String) parsed.get("opstinaPrebivalista"));
                docReq.setPol((String) parsed.get("pol"));
                docReq.setProfesija((String) parsed.get("profesija"));
                docReq.setUlicaPrebivalista((String) parsed.get("ulicaPrebivalista"));
                docReq.setStatus((String) parsed.get("status"));
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(PersoCentar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ParseException ex) {
            Logger.getLogger(PersoCentar.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return docReq;
    }
    
}
