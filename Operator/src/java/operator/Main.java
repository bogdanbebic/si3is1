/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operator;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import operator.panels.MainPanel;
import javax.swing.*;

/**
 *
 * @author Bogdan
 */
public class Main {
    
    @Resource(lookup = "jms/__defaultConnectionFactory")
    public static ConnectionFactory connectionFactory;
    
    @Resource(lookup = "jmsQueue")
    public static javax.jms.Queue jmsQueue;
    
    public static TerminCentar terminCentar = new TerminCentar();
    
    public static PersoCentar persoCentar = new PersoCentar();
    
    public static EntityManagerFactory emf
            = Persistence.createEntityManagerFactory(
                    "OperatorPU"
            );
    
    public static MainPanel mainPanel = new MainPanel();

    private static void showGui() {
        JFrame frame = new JFrame("Operator");
        frame.setSize(750, 750);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(mainPanel);
        frame.setVisible(true);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        showGui();
    }
    
}
