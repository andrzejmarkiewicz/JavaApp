/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.MainWindow.SidePanel;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;

/**
 *
 * @author andrzej
 */
public class ButtonForPanelLeft extends JButton {
    
    public ButtonForPanelLeft(String text) {
        super(text);
        
        setBorderPainted(true);
        setBackground(new Color(86, 157, 219));
//        setBackground(Color.GRAY); // BLOCKED COLOR
        setFocusPainted(false);
        setPreferredSize(new Dimension(180, 25));
    }
    
}
