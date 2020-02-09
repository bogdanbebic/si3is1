/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operator.panels;

import javax.persistence.EntityManager;
import javax.swing.*;
import operator.Main;
import operator.entity.DocumentRequest;

/**
 *
 * @author Bogdan
 */
public class RequestsInfoPanel extends JPanel {
    private final JButton backButton = new JButton("Back to menu");
    private final JLabel label = new JLabel("Requests info:");
    
    {
        this.backButton.addActionListener(e -> {
            Main.mainPanel.showNextAtivePanel(MainPanel.MENU_PANEL_NAME);
        });
    }
    
    private JLabel idLabel = new JLabel("ID of request (zero padded 7 digits):");
    private JTextField idTextField = new JTextField();
    
    private JButton refreshButton = new JButton("Refresh");
    {
        this.refreshButton.addActionListener(e -> {
            String idRequest = this.idTextField.getText();
            if (idRequest.length() != 7) {
                JOptionPane.showMessageDialog(this, "Length must be 7", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            try {
                Integer.parseUnsignedInt(idRequest);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID must be integer", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            refreshDocument("17011" + idRequest);
            
            System.out.println("REFRESH");
        });
    }
    
    private JLabel labelStatus = new JLabel("Status: ");
    private JLabel requestStatusLabel = new JLabel("");
    DocumentRequest docReq = null;
    
    private void refreshDocument(String id) {
        this.docReq = Main.persoCentar.getDocumentRequest(id);
        if (docReq == null) {
            this.requestStatusLabel.setText("Error");
            return;
        }
        
        EntityManager em = Main.emf.createEntityManager();
        int idKey = ((DocumentRequest)em.createNamedQuery("DocumentRequest.findByJmbg")
                .setParameter("jmbg", docReq.getJmbg()).getResultList().get(0)).getId();
        
        docReq.setId(idKey);
        em.getTransaction().begin();
        em.merge(docReq);
        em.flush();
        em.getTransaction().commit();
        System.out.println("TRANSACTION COMPLETE");
        
        this.requestStatusLabel.setText(docReq.getStatus());
    }
    
    private final JButton deliveryButton = new JButton("Deliver");
    {
        this.deliveryButton.addActionListener(e -> {
            if (docReq == null) {
                return;
            }
            
            if (this.docReq.getStatus().equals("proizveden")) {
                docReq.setStatus("Urucen");
                
                EntityManager em = Main.emf.createEntityManager();
                em.getTransaction().begin();
                em.merge(docReq);
                em.flush();
                em.getTransaction().commit();
                
                requestStatusLabel.setText(docReq.getStatus());
                System.out.println("DELIVERED");   
            }
        });
    }
    
    // sets component alignment
    {
        this.backButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        this.label.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        
        this.idLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        this.idTextField.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        this.refreshButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        
        this.labelStatus.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        this.requestStatusLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        
        this.deliveryButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
    }
    
    // lays out components
    {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createVerticalStrut(10));
        this.add(this.backButton);
        this.add(Box.createVerticalStrut(10));
        this.add(this.label);
        this.add(Box.createVerticalStrut(10));
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(this.idLabel);
        panel.add(this.idTextField);
        panel.add(Box.createVerticalStrut(50));
        panel.add(this.refreshButton);
        
        panel.add(this.labelStatus);
        panel.add(this.requestStatusLabel);
        
        panel.add(this.deliveryButton);
        
        JPanel horizontalPanel = new JPanel();
        horizontalPanel.add(panel);
        
        this.add(horizontalPanel);
        this.add(Box.createVerticalStrut(10));
    }
}
