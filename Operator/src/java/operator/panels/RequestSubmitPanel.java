/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operator.panels;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.*;
import operator.Main;

/**
 *
 * @author Bogdan
 */
public class RequestSubmitPanel extends JPanel {
    private final JButton backButton = new JButton("Back to menu");
    private final JLabel label = new JLabel("Request submit");
    
    {
        this.backButton.addActionListener(e -> {
            Main.mainPanel.showNextAtivePanel(MainPanel.MENU_PANEL_NAME);
        });
    }
    
    private final JButton submitButton = new JButton("Submit");

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
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createVerticalStrut(10));
        this.add(this.backButton);
        this.add(Box.createVerticalStrut(10));
        this.add(this.label);
        this.add(Box.createVerticalStrut(10));
        this.add(new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.blue);
                g.fillRect(0, 0, this.getWidth() - 1, this.getHeight() - 1);
            }
            
        });
        this.add(Box.createVerticalStrut(10));
        this.add(this.submitButton);
        this.add(Box.createVerticalStrut(10));
    }
}
