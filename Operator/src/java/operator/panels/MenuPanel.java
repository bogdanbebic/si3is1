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
public class MenuPanel extends JPanel {
    private JButton submitButton    = new JButton("Submit request");
    private JButton infoButton      = new JButton("Requests info");
    
    {
        this.submitButton.addActionListener(e -> {
            Main.mainPanel.showNextAtivePanel(MainPanel.SUBMIT_PANEL_NAME);
        });
        
        this.infoButton.addActionListener(e -> {
            Main.mainPanel.showNextAtivePanel(MainPanel.INFO_PANEL_NAME);
        });
    }
    
    {
        this.add(this.submitButton);
        this.add(this.infoButton);
    }
}
