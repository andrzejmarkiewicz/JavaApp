/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.MainWindow.MainPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.plaf.basic.BasicBorders;

/**
 *
 * @author andrzej
 */
public class QuestionAndRadioButtonsPanel extends JPanel {

    public JRadioButton getButton(String text) {
        return map.get(text);
    }

    private JLabel panelHeader;
    private JTextArea textArea;
    private JRadioButton buttonAnswer_1;
    private JRadioButton buttonAnswer_2;
    private JRadioButton buttonAnswer_3;
    private ButtonGroup answerGroup;
    private Map<String, JRadioButton> map = new HashMap<>();
    
    public QuestionAndRadioButtonsPanel(String heading) {
        
        this.setBackground(new Color(175, 226, 189));
        this.setLayout(new BorderLayout());
        
        // HEADER
        panelHeader = new JLabel();
        panelHeader.setHorizontalAlignment(SwingConstants.CENTER);
        panelHeader.setText(heading);
        this.add(panelHeader, BorderLayout.NORTH);
        
        // TEXTAREA/QUESTION
        textArea = new JTextArea();
        configureTextArea();
        
        // MAIN PANEL CONTAINING TEXTAREA + RADIOBUTTONS
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(175, 226, 189));
        
        mainPanel.setLayout(new GridLayout(2, 1));
        mainPanel.add(textArea);
        ////
//        JPanel panel = new JPanel();
//        panel.setBackground(new Color(174,217,246));
//        panel.setLayout(new BorderLayout());
//        panel.add(textArea, BorderLayout.SOUTH);
//        JLabel label = new JLabel("QUESTION");
//        panel.add(label, BorderLayout.NORTH);
//        label.setHorizontalAlignment(SwingConstants.CENTER);
//        mainPanel.add(panel);
        
        
        ////
        this.add(mainPanel, BorderLayout.CENTER);
        
        // PANEL FOR RADIO BUTTONS
        JPanel panelForRadioButtons = new JPanel();
        panelForRadioButtons.setBackground(new Color(174,217,246));
        panelForRadioButtons.setLayout(new GridLayout(3, 1));
        mainPanel.add(panelForRadioButtons);
        
        buttonAnswer_1 = new JRadioButton("");
        buttonAnswer_2 = new JRadioButton("");
        buttonAnswer_3 = new JRadioButton("");
        buttonAnswer_1.setBackground(new Color(174,217,246));
        buttonAnswer_2.setBackground(new Color(174,217,246));
        buttonAnswer_3.setBackground(new Color(174,217,246));
        
        answerGroup = new ButtonGroup();
        
        answerGroup.add(buttonAnswer_1);
        answerGroup.add(buttonAnswer_2);
        answerGroup.add(buttonAnswer_3);
        panelForRadioButtons.add(buttonAnswer_1);
        panelForRadioButtons.add(buttonAnswer_2);
        panelForRadioButtons.add(buttonAnswer_3);
        
    }

    private void configureTextArea() {
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        textArea.setFocusable(false);
        textArea.setBackground(new Color(175, 226, 189));
        
        Dimension dim = textArea.getPreferredSize();
        dim.height = 100;
        textArea.setPreferredSize(dim);
    }
    
    public void setTextForQuestionInTextArea(String text) {
        textArea.setText("");
        textArea.append(text);
    }
    
    public void setTextForRadioButtons(String text) {
        String[] parts = text.split(",");
        
        
        buttonAnswer_1.setText(parts[0]);
        buttonAnswer_2.setText(parts[1]);
        buttonAnswer_3.setText(parts[2]);
        
        buttonAnswer_1.setActionCommand(parts[0]);
        buttonAnswer_2.setActionCommand(parts[1]);
        buttonAnswer_3.setActionCommand(parts[2]);
        
        map.put(parts[0], buttonAnswer_1);
        map.put(parts[1], buttonAnswer_2);
        map.put(parts[2], buttonAnswer_3);
                
    }
    
    public ButtonGroup getAnswerGroup() {
        return answerGroup;
    }

    public JRadioButton getButtonAnswer_1() {
        return buttonAnswer_1;
    }

    public JRadioButton getButtonAnswer_2() {
        return buttonAnswer_2;
    }

    public JRadioButton getButtonAnswer_3() {
        return buttonAnswer_3;
    }
}
