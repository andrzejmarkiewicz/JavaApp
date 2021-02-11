/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.MainWindow.SidePanel;

import gui.Listeners.SidePanelButtonListener;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author andrzej
 */
public class SidePaneInPanel extends JPanel {
    
    private ButtonForPanelLeft[] buttonList;
    private JLabel[] iconList;
    
    private Map<String, JLabel> mapButtonTextToIcon;
    private SidePanelButtonListener listener;

    
    public SidePaneInPanel() {
        initializeButtons();
        initializeIcons();
        initializeHashMapButtonTextToIcon();
        
        layoutComponents();
        
        for(ButtonForPanelLeft button : buttonList) {
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    listener.buttonPressed(button.getText());
                }
            });
        }
    }
    
    private void initializeButtons() {
        // 20 elements because 20 Lektions are prepared
        // will be extended in the future
        buttonList = new ButtonForPanelLeft[20];        
        for(int i=0; i<20; i++) {
            String lessonText = "Lektion ";
            lessonText += (i+1);
            ButtonForPanelLeft button = new ButtonForPanelLeft(lessonText);
            buttonList[i] = button;
        }
    }
    
    private void initializeIcons() {
        // 20 elements because 20 Lektions are prepared
        // will be extended in the future
        iconList = new JLabel[20];
        for(int i=0; i<20; i++) {
            JLabel icon = new JLabel();
            iconList[i] = icon;
        }
    }
    
    private void initializeHashMapButtonTextToIcon() {
        mapButtonTextToIcon = new HashMap<>();
        for(int i=0; i<buttonList.length; i++) {
            mapButtonTextToIcon.put(buttonList[i].getText(), iconList[i]);
        }
    }
    
    private void layoutComponents() {
        setBackground(new Color(174,217,246));
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.CENTER;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.insets = new Insets(10, 10, 10, 10);
        
        placeButtons(gc);
        
        // go one 'x' to the right and reset 'y'
        gc.gridx = 1;
        gc.gridy = 0;
        
        placeIcons(gc);
        
    }
    
    private void placeButtons(GridBagConstraints gc) {
        for(ButtonForPanelLeft button : buttonList) {
            add(button, gc);
            gc.gridy++;
        }
    }
    
    private void placeIcons(GridBagConstraints gc) {
        for(JLabel label : iconList) {
            label.setIcon(new ImageIcon(getClass().getResource("/icons/testIcon.png")));
            add(label, gc);
            gc.gridy++;
        }
    }

    public void setButtonListener(SidePanelButtonListener sidePanelButtonListener) {
        this.listener = sidePanelButtonListener;
    }
    
    public void updateIcons(Map<String, Boolean> mapLessonNumberToLessonStatus) {
        // 19 iteration because only 19 lessons are prepared
        for(int i=0; i<=19; i++) {
            if(mapLessonNumberToLessonStatus.get(buttonList[i].getText()) == true) {
                setIconAnswerCorrect(i);
            }
        }
    }
    
    private void setIconAnswerCorrect(int i) {
        iconList[i].setIcon(new ImageIcon(getClass()
                   .getResource("/icons/answerCorrectIcon.png")));
    }
}
