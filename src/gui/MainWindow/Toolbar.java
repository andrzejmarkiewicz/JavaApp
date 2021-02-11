/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.MainWindow;

import gui.Listeners.ExitButtonListener;
import controller.Controller;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 *
 * @author andrzej
 */
public class Toolbar extends JPanel {
    
    private JButton exitButton;
    private ExitButtonListener exitButtonListener;

    public Toolbar() {
        
        exitButton = new JButton("Exit");
        
        configureToolbar();
        configureTextPanel();
        
        add(exitButton);
        
    }
    
    private void configureToolbar() {
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
        setLayout(new FlowLayout(FlowLayout.RIGHT));
        setBackground(new Color(174,217,246));
    }
    
    private void configureTextPanel() {
        exitButton.setBackground(new Color(86, 157, 219));
        exitButton.setBorderPainted(true);
        exitButton.setFocusPainted(false);
        exitButton.setPreferredSize(new Dimension(70, 25));
        
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("SAVING TO DATABASE");
                exitButtonListener.exitButtonPressed();
                
            }
        });
    }

    public void setExitButtonListener(ExitButtonListener exitButtonListener) {
        this.exitButtonListener = exitButtonListener;
    } 
}
