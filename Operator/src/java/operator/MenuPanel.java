/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operator;

import javax.swing.*;

/**
 *
 * @author Bogdan
 */
public class MenuPanel extends JPanel {
    private JButton submitButton    = new JButton("Submit request");
    private JButton infoButton      = new JButton("Requests info");
    
    {
        this.submitButton.addActionListener(e -> {
            Main.mainPanel.showNextAtivePanel(MainPanel.submitPanelName);
        });
        
        this.infoButton.addActionListener(e -> {
            Main.mainPanel.showNextAtivePanel(MainPanel.infoPanelName);
        });
    }
    
    {
        this.add(this.submitButton);
        this.add(this.infoButton);
    }
}
