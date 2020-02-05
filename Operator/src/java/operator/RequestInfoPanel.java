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
public class RequestInfoPanel extends JPanel {
    private JLabel label = new JLabel("Request info");
    private JButton backButton = new JButton("Back to menu");
    
    {
        this.backButton.addActionListener(e -> {
            Main.mainPanel.showNextAtivePanel(MainPanel.menuPanelName);
        });
    }
    
    {
        this.add(this.backButton);
        this.add(this.label);
    }
}
