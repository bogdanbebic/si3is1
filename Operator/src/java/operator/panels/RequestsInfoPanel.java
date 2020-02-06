/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operator.panels;

import javax.swing.*;
import operator.Main;

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
            
            // TODO: send refresh to server
            
            System.out.println("REFRESH");
        });
    }
    
    // sets component alignment
    {
        this.backButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        this.label.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        
        this.idLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        this.idTextField.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        this.refreshButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
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
        
        JPanel horizontalPanel = new JPanel();
        horizontalPanel.add(panel);
        
        this.add(horizontalPanel);
        this.add(Box.createVerticalStrut(10));
    }
}
