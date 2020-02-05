/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operator;

import java.awt.CardLayout;
import javax.swing.*;

/**
 *
 * @author Bogdan
 */
public class MainPanel extends JPanel {
    private JPanel menuPanel = new MenuPanel();
    static final String menuPanelName = "MENU_PANEL";
    
    private JPanel submitPanel = new RequestSubmitPanel();
    static final String submitPanelName = "SUBMIT_PANEL";
    
    private JPanel infoPanel = new RequestInfoPanel();
    static final String infoPanelName = "INFO_PANEL";
    
    {
        this.setLayout(new CardLayout());
        this.add(this.menuPanel, menuPanelName);
        this.add(this.submitPanel, submitPanelName);
        this.add(this.infoPanel, infoPanelName);
    }
    
    void showNextAtivePanel(String nextActivePanel) {
        ((CardLayout)this.getLayout()).show(this, nextActivePanel);
    }
    
}
