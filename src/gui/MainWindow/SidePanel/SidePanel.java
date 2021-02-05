/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.MainWindow.SidePanel;

import gui.Listeners.SidePanelButtonListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author andrzej
 */
public class SidePanel extends JPanel {
    
    private SidePanelButtonListener listener;
    private SidePaneInPanel panel;
    
    
    public SidePanel() { 
  
        setLayout(new BorderLayout());
        panel = new SidePaneInPanel();
        add(new JScrollPane(panel));
        setDefaultSize();
        
    }

    private void setDefaultSize() {
        Dimension dim = getPreferredSize();
        dim.width = 270;
        setPreferredSize(dim);
        
        setBackground(new Color(174,217,246));
    }

    public void setButtonListener(SidePanelButtonListener sidePanelButtonListener) {
            this.listener = sidePanelButtonListener;
            panel.setButtonListener(sidePanelButtonListener);
            
    }
    
    public JLabel getIconFromButtonText(String text) {
        return panel.getIconFromButtonText(text);
    }

    public void setMapLessonNumberToLessonStatus(Map<String, Boolean> map) {
        panel.setMapLessonNumberToLessonStatus(map);
        
    }

    public void checkLessonStatusAndSetIcon() {
        panel.checkLessonStatusAndSetIcon();
    }
}
