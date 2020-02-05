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
public class RequestSubmitPanel extends JPanel {
    private JButton backButton = new JButton("Back to menu");
    private JLabel label = new JLabel("Request submit");
    
    {
        this.backButton.addActionListener(e -> {
            Main.mainPanel.showNextAtivePanel(MainPanel.MENU_PANEL_NAME);
        });
    }
    
    private JButton submitButton = new JButton("Submit");

    {
        this.submitButton.addActionListener(e -> {
            // TODO: implement
            System.out.println("SUBMIT");
        });
    }
    {
        this.backButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        this.label.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        this.submitButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
    }
    
    {
        this.add(this.backButton);
        this.add(this.label);
        this.add(this.submitButton);
        this.add(Box.createVerticalGlue());
    }
}
