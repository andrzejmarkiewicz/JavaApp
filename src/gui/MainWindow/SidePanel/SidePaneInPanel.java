/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.MainWindow.SidePanel;

import gui.MainWindow.SidePanel.ButtonForPanelLeft;
import gui.Listeners.SidePanelButtonListener;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author andrzej
 */
public class SidePaneInPanel extends JPanel {
    
    private ButtonForPanelLeft button1;
    private ButtonForPanelLeft button2;
    private ButtonForPanelLeft button3;
    private ButtonForPanelLeft button4;
    private ButtonForPanelLeft button5;
    private ButtonForPanelLeft button6;
    private ButtonForPanelLeft button7;
    private ButtonForPanelLeft button8;
    private ButtonForPanelLeft button9;
    private ButtonForPanelLeft button10;
    private ButtonForPanelLeft button11;
    private ButtonForPanelLeft button12;
    private ButtonForPanelLeft button13;
    private ButtonForPanelLeft button14;
    private ButtonForPanelLeft button15;
    private ButtonForPanelLeft button16;
    private ButtonForPanelLeft button17;
    private ButtonForPanelLeft button18;
    private ButtonForPanelLeft button19;
    private ButtonForPanelLeft button20;
    
    private JLabel icon1;
    private JLabel icon2;
    private JLabel icon3;
    private JLabel icon4;
    private JLabel icon5;
    private JLabel icon6;
    private JLabel icon7;
    private JLabel icon8;
    private JLabel icon9;
    private JLabel icon10;
    private JLabel icon11;
    private JLabel icon12;
    private JLabel icon13;
    private JLabel icon14;
    private JLabel icon15;
    private JLabel icon16;
    private JLabel icon17;
    private JLabel icon18;
    private JLabel icon19;
    private JLabel icon20;
    
    private Map<String, JLabel> mapButtonTextToIcon;
    
    
    private SidePanelButtonListener listener;
    
    private ButtonForPanelLeft[] list;
    private JLabel[] iconList;
    
    
    private Map<String, Boolean> mapLessonNumberToLessonStatus;

    
    public SidePaneInPanel() {
        
        button1 = new ButtonForPanelLeft("Lektion 1");
        button2 = new ButtonForPanelLeft("Lektion 2");
        button3 = new ButtonForPanelLeft("Lektion 3");
        button4 = new ButtonForPanelLeft("Lektion 4");
        button5 = new ButtonForPanelLeft("Lektion 5");
        button6 = new ButtonForPanelLeft("Lektion 6");
        button7 = new ButtonForPanelLeft("Lektion 7");
        button8 = new ButtonForPanelLeft("Lektion 8");
        button9 = new ButtonForPanelLeft("Lektion 9");
        button10 = new ButtonForPanelLeft("Lektion 10");
        button11 = new ButtonForPanelLeft("Lektion 11");
        button12 = new ButtonForPanelLeft("Lektion 12");
        button13 = new ButtonForPanelLeft("Lektion 13");
        button14 = new ButtonForPanelLeft("Lektion 14");
        button15 = new ButtonForPanelLeft("Lektion 15");
        button16 = new ButtonForPanelLeft("Lektion 16");
        button17 = new ButtonForPanelLeft("Lektion 17");
        button18 = new ButtonForPanelLeft("Lektion 18");
        button19 = new ButtonForPanelLeft("Lektion 19");
        button20 = new ButtonForPanelLeft("Lektion 20");
        
        
        list = new ButtonForPanelLeft[]{button1, button2, button3, button4,
            button5, button6, button7, button8, button9, button10, button11,
            button12, button13, button14, button15, button16, button17,
            button18, button19, button20};
        
        
        icon1 = new JLabel();
        icon2 = new JLabel();
        icon3 = new JLabel();
        icon4 = new JLabel();
        icon5 = new JLabel();
        icon6 = new JLabel();
        icon7 = new JLabel();
        icon8 = new JLabel();
        icon9 = new JLabel();
        icon10 = new JLabel();
        icon11 = new JLabel();
        icon12 = new JLabel();
        icon13 = new JLabel();
        icon14 = new JLabel();
        icon15 = new JLabel();
        icon16 = new JLabel();
        icon17 = new JLabel();
        icon18 = new JLabel();
        icon19 = new JLabel();
        icon20 = new JLabel();
        
        iconList = new JLabel[]{icon1, icon2, icon3, icon4, icon5, icon6,
            icon7, icon8, icon9, icon10, icon11, icon12, icon13, icon14,
            icon15, icon16, icon17, icon18, icon19, icon20};
        
        
        mapButtonTextToIcon = new HashMap<>();
        for(int i=0; i<list.length; i++) {
            mapButtonTextToIcon.put(list[i].getText(), iconList[i]);
            
        }
        
        
        layoutComponents();
        
        
        for(ButtonForPanelLeft button : list) {
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
//                    icon1.setIcon(new ImageIcon(getClass().getResource("/gui/ok.png")));
                    listener.buttonPressed(button.getText());
                }
            });
        }
    }

    public JLabel getIconFromButtonText(String text) {
        return mapButtonTextToIcon.get(text);
    }
    
    private void layoutComponents() {
        
        setBackground(new Color(174,217,246));
        
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        
        
        //// FIRST BUTTON   //////////////////////////
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.CENTER;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.insets = new Insets(10, 10, 10, 10);
        
        
        
        for(ButtonForPanelLeft button : list) {
            add(button, gc);
            gc.gridy++;
        }
        
        gc.gridx = 1;
        gc.gridy = 0;

        
        for(JLabel label : iconList) {
//            label.setIcon(new ImageIcon(getClass().getResource("/gui/testIcon.png")));
            label.setIcon(new ImageIcon(getClass().getResource("/icons/testIcon.png")));

            add(label, gc);
            gc.gridy++;
        }
    }

    public void setButtonListener(SidePanelButtonListener sidePanelButtonListener) {
        this.listener = sidePanelButtonListener;
    }
    
    public void setMapLessonNumberToLessonStatus(Map<String, Boolean> map) {
        this.mapLessonNumberToLessonStatus = map;
    }
    
    // TEST TODAY
    public void checkLessonStatusAndSetIcon() {
        
        int count = 0;
        for(JLabel icon : iconList) {
            // TEST
//            System.out.println("TEST----------------------------------->");
            String textButton = list[count].getText();
            System.out.println(textButton);
            if(count == 19) break; // WE HAVE ONLY LESSON 1 and 2 right now!!
            // if condition not exist, then null pointer exception
            // because method what to take so many booleans from map as exist in list.  
            
            if(mapLessonNumberToLessonStatus.get(list[count].getText()) == true) {
                icon
                    .setIcon(new ImageIcon(getClass()
                    .getResource("/icons/answerCorrectIcon.png")));
            }
            count++;
        }
    }

}
