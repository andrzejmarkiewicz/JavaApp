/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.MainWindow.MainPanel;

import gui.Listeners.QuestionButtonListener;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author andrzej
 */
public class MainPanel extends JPanel {
    
    private TextPanel textPanel;
    private QuestionsPanel questionsPanel;
    private QuestionButtonListener questionButtonListener;
    private JPanel cardPanel;
    private JButton goToQuestionsButton;
    private MainPanelToolbar toolbar;
    
    
    public JPanel getCardPanel() {
        return cardPanel;
    }

    public TextPanel getTextPanel() {
        return textPanel;
    }

    public QuestionsPanel getQuestionsPanel() {
        return questionsPanel;
    }
    
    public MainPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(175, 226, 189));
        

        JPanel panelForButton = new JPanel();
        panelForButton.setLayout(new BorderLayout());
        add(panelForButton, BorderLayout.SOUTH);
        Dimension dim = panelForButton.getPreferredSize();
        dim.height = 50;
        panelForButton.setPreferredSize(dim);
        panelForButton.setBackground(new Color(175, 226, 189));
        
        
        
        goToQuestionsButton = new JButton("Check");
        goToQuestionsButton.setBackground(new Color(86, 157, 219));
        goToQuestionsButton.setBorderPainted(true);
        goToQuestionsButton.setFocusPainted(false);
        goToQuestionsButton.setPreferredSize(new Dimension(140, 60));
        panelForButton.add(goToQuestionsButton, BorderLayout.EAST);
        
        textPanel = new TextPanel();
        textPanel.setPreferredSize(new Dimension(200, 100));
        

        toolbar = new MainPanelToolbar();
        add(toolbar, BorderLayout.NORTH);
        
        
        
        questionsPanel = new QuestionsPanel();
  
        
        cardPanel = new JPanel();
        add(cardPanel, BorderLayout.CENTER);
        cardPanel.setLayout(new CardLayout());
        
        cardPanel.add(textPanel, "text");
        cardPanel.add(questionsPanel, "question");
        
        
        
        
        goToQuestionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                questionButtonListener.questionButtonPressed("ABC");
//                textPanel.setVisible(false);

            }
        });        
        
    }

    public MainPanelToolbar getToolbar() {
        return toolbar;
    }

    public void setTextForMainPanel(String incomingText) {
        textPanel.addText(incomingText);
    }
    
    public void clearTextArea() {
        textPanel.textPanelClear();
    }

    public void setQuestionButtonListener(QuestionButtonListener questionButtonListener) {
        this.questionButtonListener = questionButtonListener;
        
    }
}
