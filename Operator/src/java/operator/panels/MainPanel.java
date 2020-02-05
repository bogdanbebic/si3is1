/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operator.panels;

import java.awt.CardLayout;
import javax.swing.*;

/**
 *
 * @author Bogdan
 */
public class MainPanel extends JPanel {
    private final JPanel menuPanel = new MenuPanel();
    static final String MENU_PANEL_NAME = "MENU_PANEL";
    
    private final JPanel submitPanel = new RequestSubmitPanel();
    static final String SUBMIT_PANEL_NAME = "SUBMIT_PANEL";
    
    private final JPanel infoPanel = new RequestsInfoPanel();
    static final String INFO_PANEL_NAME = "INFO_PANEL";
    
    {
        this.setLayout(new CardLayout());
        this.add(this.menuPanel, MENU_PANEL_NAME);
        this.add(this.submitPanel, SUBMIT_PANEL_NAME);
        this.add(this.infoPanel, INFO_PANEL_NAME);
    }
    
    void showNextAtivePanel(String nextActivePanel) {
        ((CardLayout)this.getLayout()).show(this, nextActivePanel);
    }
    
}
