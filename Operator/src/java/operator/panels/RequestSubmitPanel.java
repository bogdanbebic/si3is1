/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operator.panels;

import java.awt.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.swing.*;
import operator.Main;
import operator.RequestFormData;
import operator.entity.DocumentRequest;

/**
 *
 * @author Bogdan
 */
public class RequestSubmitPanel extends JPanel {
    private final JButton backButton = new JButton("Back to menu");
    private final JLabel label = new JLabel("Request submit:");
    
    // form elements
    private final JLabel jmbgLabel      = new JLabel("JMBG:");
    private final JTextField jmbgText    = new JTextField();
    private final JLabel nameLabel      = new JLabel("Name:");
    private final JTextField nameText    = new JTextField();
    private final JLabel surnameLabel   = new JLabel("Surame:");
    private final JTextField surnameText = new JTextField();
    private final JLabel nameOfMotherLabel      = new JLabel("Name of mother:");
    private final JTextField nameOfMotherText    = new JTextField();
    private final JLabel surnameOfMotherLabel   = new JLabel("Surname of mother:");
    private final JTextField surnameOfMotherText = new JTextField();
    private final JLabel nameOfFatherLabel      = new JLabel("Name of father:");
    private final JTextField nameOfFatherText    = new JTextField();
    private final JLabel surnameOfFatherLabel   = new JLabel("Surame of father:");
    private final JTextField surnameOfFatherText = new JTextField();
    private final JLabel genderLabel = new JLabel("Gender:");
    private final ButtonGroup genderButtonGroup = new ButtonGroup();
    private final JRadioButton maleButton = new JRadioButton("male", true);
    private final JRadioButton femaleButton = new JRadioButton("female", false);
    {
        this.genderButtonGroup.add(this.maleButton);
        this.genderButtonGroup.add(this.femaleButton);
    }
    
    private final JLabel dateOfBirthLabel = new JLabel("Date of birth (YYYY-MM-DD):");
    private final JTextField dateOfBirthText = new JTextField("YYYY-MM-DD");
    private final JLabel nationalityLabel   = new JLabel("Nationality:");
    private final JTextField nationalityText = new JTextField();
    private final JLabel professionLabel    = new JLabel("Profession:");
    private final JTextField professionText  = new JTextField();
    private final JLabel maritalStatusLabel     = new JLabel("Marital status:");
    private final JTextField maritalStatusText   = new JTextField();
    private final JLabel municipalityLabel    = new JLabel("Municipality:");
    private final JTextField municipalityText  = new JTextField();
    private final JLabel streetLabel    = new JLabel("Street:");
    private final JTextField streetText  = new JTextField();
    private final JLabel streetNumberLabel    = new JLabel("Street number:");
    private final JTextField streetNumberText  = new JTextField();
    
    private void addFormElementsToPanel(JPanel panel) {
        panel.add(this.jmbgLabel);
        panel.add(this.jmbgText);
        
        panel.add(this.nameLabel);
        panel.add(this.nameText);
        
        panel.add(this.surnameLabel);
        panel.add(this.surnameText);
        
        panel.add(this.nameOfMotherLabel);
        panel.add(this.nameOfMotherText);
        
        panel.add(this.surnameOfMotherLabel);
        panel.add(this.surnameOfMotherText);
        
        panel.add(this.nameOfFatherLabel);
        panel.add(this.nameOfFatherText);
        
        panel.add(this.surnameOfFatherLabel);
        panel.add(this.surnameOfFatherText);
        
        panel.add(this.genderLabel);
        JPanel genderPanel = new JPanel(new FlowLayout());
        genderPanel.add(this.maleButton);
        genderPanel.add(this.femaleButton);
        panel.add(genderPanel);
        
        panel.add(this.dateOfBirthLabel);
        panel.add(this.dateOfBirthText);
        
        panel.add(this.nationalityLabel);
        panel.add(this.nationalityText);
        
        panel.add(this.professionLabel);
        panel.add(this.professionText);
        
        panel.add(this.maritalStatusLabel);
        panel.add(this.maritalStatusText);
        
        panel.add(this.municipalityLabel);
        panel.add(this.municipalityText);
        
        panel.add(this.streetLabel);
        panel.add(this.streetText);
        
        panel.add(this.streetNumberLabel);
        panel.add(this.streetNumberText);
    }
    
    {
        this.backButton.addActionListener(e -> {
            Main.mainPanel.showNextAtivePanel(MainPanel.MENU_PANEL_NAME);
        });
    }
    
    private final JButton submitButton = new JButton("Submit");

    {
        this.submitButton.addActionListener(e -> {
            addSubmitRequestToDatabase();
            System.out.println("SUBMIT");
        });
    }
    
    private void addSubmitRequestToDatabase() {
        RequestFormData reqFormData = new RequestFormData(RequestFormData.STATUS_CREATED);
        reqFormData.setJmbg(this.jmbgText.getText());
        reqFormData.setName(this.nameText.getText());
        reqFormData.setSurname(this.surnameText.getText());
        reqFormData.setNameOfMother(this.nameOfMotherText.getText());
        reqFormData.setSurnameOfMother(this.surnameOfMotherText.getText());
        reqFormData.setNameOfFather(this.nameOfFatherText.getText());
        reqFormData.setSurnameOfFather(this.surnameOfFatherText.getText());
        reqFormData.setGender(this.maleButton.isSelected() ? "M" : "F");
        reqFormData.setDateOfBirth(this.dateOfBirthText.getText());
        reqFormData.setNationality(this.nationalityText.getText());
        reqFormData.setProfession(this.professionText.getText());
        reqFormData.setMaritalStatus(this.maritalStatusText.getText());
        reqFormData.setMunicipality(this.municipalityText.getText());
        reqFormData.setStreet(this.streetText.getText());
        reqFormData.setStreetNumber(this.streetNumberText.getText());
        
        System.out.println(reqFormData);
        
        if (!reqFormData.checkData()) {
            JOptionPane.showMessageDialog(this, "Invalid data input", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        EntityManager em = Main.emf.createEntityManager();
        try {
            EntityTransaction et = em.getTransaction();
            et.begin();
            DocumentRequest docReq = reqFormData.toDocumentRequest();
            em.persist(docReq);
            et.commit();
        } finally {
            em.close();
        }
    }
    
    {
        this.backButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        this.label.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        this.submitButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
    }
    
    // lays out components
    {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createVerticalStrut(10));
        this.add(this.backButton);
        this.add(Box.createVerticalStrut(10));
        this.add(this.label);
        this.add(Box.createVerticalStrut(10));
        
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        this.addFormElementsToPanel(formPanel);
        
        JPanel horizontalPanel = new JPanel();
        horizontalPanel.setLayout(new BoxLayout(horizontalPanel, BoxLayout.X_AXIS));
        horizontalPanel.add(Box.createHorizontalStrut(250));
        horizontalPanel.add(formPanel);
        horizontalPanel.add(Box.createHorizontalStrut(250));

        this.add(horizontalPanel);
        
        this.add(Box.createVerticalStrut(10));
        this.add(this.submitButton);
        this.add(Box.createVerticalStrut(10));
    }
}
