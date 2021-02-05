/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.MainWindow.MainPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author andrzej
 */
public class TextPanel extends JPanel {
    
    private JTextArea textArea;

    public TextPanel() {
        textArea = new JTextArea();
        setLayout(new BorderLayout());
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        
        setDefaultSize();
        textArea.setPreferredSize(new Dimension(50, 100));
        
        Font font = textArea.getFont();
        float size = font.getSize() + 2.0f;
        
        textArea.setFont( font.deriveFont(size) );        
        
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        textArea.setFocusable(false);
    }
    
    public void addText(String text) {
        System.out.println("TEST ->>");
        System.out.println(text);
        textArea.append(text);
        
    }
    private void setDefaultSize() {
        Dimension dim = getPreferredSize();
        dim.width = 300;
        setPreferredSize(dim);
        
        textArea.setBackground(new Color(175, 226, 189));
    }
    
    public void textPanelClear() {
        textArea.setText("");
    }
    
}
