/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.MainWindow.MainPanel;

import gui.Listeners.AnswerCheckerListener;
import gui.Listeners.NextLessonListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;


/**
 *
 * @author andrzej
 */
public class QuestionsPanel extends JPanel {
        
    private JPanel panelCenterMain;
    private QuestionAndRadioButtonsPanel panelWest;
    private QuestionAndRadioButtonsPanel panelCenter;
    private QuestionAndRadioButtonsPanel panelEast;
    private String[] answerToQuestion = new String[3];
    private AnswerCheckerListener answerCheckerListener;
    private String lessonsNumber;
    private NextLessonListener nextLessonListener;

    
    private boolean isAllowedToGoToNextLesson = false;
    
    public void setIsNotAllowableToGoToNextLesson() {
        isAllowedToGoToNextLesson = false;
    }
    
    
    public QuestionsPanel() {
        
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(175, 226, 189));
        
        
        panelWest = new QuestionAndRadioButtonsPanel("Question 1");
        panelCenter = new QuestionAndRadioButtonsPanel("Question 2");
        panelEast = new QuestionAndRadioButtonsPanel("Question 3");
        
        
        panelCenterMain = new JPanel();
        add(panelCenterMain, BorderLayout.CENTER);
        panelCenterMain.setLayout(new GridLayout(1, 3));
        panelCenterMain.add(panelWest);
        panelCenterMain.add(panelCenter);
        panelCenterMain.add(panelEast);
        
        panelWest.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panelCenter.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panelEast.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        
        ///////////////////////////////////////////////////////////////////////
        
        JPanel panelForCheckQuestionsButton = new JPanel();
        panelForCheckQuestionsButton.setLayout(new BorderLayout());
        panelForCheckQuestionsButton.setBackground(new Color(75, 226, 189));
        
        add(panelForCheckQuestionsButton, BorderLayout.SOUTH);
        
        
        Dimension dim2 = panelForCheckQuestionsButton.getPreferredSize();
        dim2.height = 100;
        panelForCheckQuestionsButton.setPreferredSize(dim2);
        panelForCheckQuestionsButton.setBorder(BorderFactory.createLineBorder(Color.GRAY));  
        
        ///////////////////////////////////////////////////////////////////////
        // BUTTONS SOUTH PANEL
        
        JButton buttonCheckAnswer = new JButton("Antworten pruefen");
        panelForCheckQuestionsButton.add(buttonCheckAnswer, BorderLayout.EAST);
        
        JButton buttonStartAgain = new JButton("noch einmal probieren");
        panelForCheckQuestionsButton.add(buttonStartAgain, BorderLayout.WEST);
        
        JButton buttonNextLesson = new JButton("naechste Lektion");
        panelForCheckQuestionsButton.add(buttonNextLesson, BorderLayout.CENTER);
        
        buttonCheckAnswer.setBackground(new Color(86, 157, 219));
        buttonCheckAnswer.setBorderPainted(true);
        buttonCheckAnswer.setFocusPainted(false);
        buttonCheckAnswer.setPreferredSize(new Dimension(230, 25));
        
        buttonStartAgain.setBackground(new Color(86, 157, 219));
        buttonStartAgain.setBorderPainted(true);
        buttonStartAgain.setFocusPainted(false);
        buttonStartAgain.setPreferredSize(new Dimension(230, 25));
        
        buttonNextLesson.setBackground(Color.GRAY);
        buttonNextLesson.setBorderPainted(true);
        buttonNextLesson.setFocusPainted(false);
        buttonNextLesson.setPreferredSize(new Dimension(230, 15));
        
        ///////////////////////////////////////////////////////////////////////
        
        
        buttonCheckAnswer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String answer_1 = panelWest.getAnswerGroup().getSelection().getActionCommand();
                String answer_2 = panelCenter.getAnswerGroup().getSelection().getActionCommand();
                String answer_3 = panelEast.getAnswerGroup().getSelection().getActionCommand();
                String[] answers = new String[]{answer_1, answer_2, answer_3};
                
                Boolean[] checkAnswerButtonPressed = 
                        answerCheckerListener.checkAnswerButtonPressed(answers, lessonsNumber);
                
                QuestionAndRadioButtonsPanel[] panels = 
                        new QuestionAndRadioButtonsPanel[]{panelWest, panelCenter, panelEast};
                
                
                int correctAnswersCounter = 0;
                for(int i=0; i<answers.length; i++) {
                    if(checkAnswerButtonPressed[i]) {
                        panels[i].getButton(answers[i]).setBackground(new Color(162, 235, 94));
                        correctAnswersCounter++;
                    } else {
                        panels[i].getButton(answers[i]).setBackground(new Color(224, 117, 81));
                    }
                }
                
                if(correctAnswersCounter == 3) {
                    buttonNextLesson.setBackground(new Color(162, 235, 94));
                    isAllowedToGoToNextLesson = true;
                }            
            }
        });
        
        
        buttonStartAgain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                clearRadioButtonsAndMakeColorDefault();
                
            }
        });
        
        
        buttonNextLesson.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                if(isAllowedToGoToNextLesson) {
                    buttonNextLesson.setBackground(Color.GRAY);
                
                    clearRadioButtonsAndMakeColorDefault();
                
                    nextLessonListener.nextLessonButtonPressed(lessonsNumber);
                } else {
                    clearRadioButtonsAndMakeColorDefault();
                }
                
            }
        });
    }
    
    private void clearRadioButtonsAndMakeColorDefault() {
        
        panelWest.getButton(panelWest
                        .getAnswerGroup()
                        .getSelection()
                        .getActionCommand())
                        .setBackground(new Color(174,217,246));
                
        panelCenter.getButton(panelCenter
                        .getAnswerGroup()
                        .getSelection()
                        .getActionCommand())
                        .setBackground(new Color(174,217,246));
                
        panelEast.getButton(panelEast
                        .getAnswerGroup()
                        .getSelection()
                        .getActionCommand())
                        .setBackground(new Color(174,217,246));
                
                
        panelWest.getAnswerGroup().clearSelection();
        panelCenter.getAnswerGroup().clearSelection();
        panelEast.getAnswerGroup().clearSelection();
    }
    
    public void setLabelForQuestion(String text) {
        String[] parts = text.split("\n");

        panelWest.setTextForQuestionInTextArea(parts[0]);
        panelCenter.setTextForQuestionInTextArea(parts[1]);
        panelEast.setTextForQuestionInTextArea(parts[2]);
        
        String antwort_1 = parts[3].trim();
        String antwort_2 = parts[4].trim();
        String antwort_3 = parts[5].trim();
        
        panelWest.setTextForRadioButtons(antwort_1);
        panelCenter.setTextForRadioButtons(antwort_2);
        panelEast.setTextForRadioButtons(antwort_3);
    }
    
    public void setTextForQuestions(String[] questions) {
        panelWest.setTextForQuestionInTextArea(questions[0]);
        panelCenter.setTextForQuestionInTextArea(questions[1]);
        panelEast.setTextForQuestionInTextArea(questions[2]);
    }
    
    public void setTextForAnswers(String[] answers) {
        panelWest.setTextForRadioButtons(answers[0]);
        panelCenter.setTextForRadioButtons(answers[1]);
        panelEast.setTextForRadioButtons(answers[2]);
    }
    
    
    public void setAnswersForQuestion(String[] rightAnswer) {
        answerToQuestion[0] = rightAnswer[0];
        answerToQuestion[1] = rightAnswer[1];
        answerToQuestion[2] = rightAnswer[2];
        
    }

    public void setAnswerCheckerListener(AnswerCheckerListener answerCheckerListener) {
        this.answerCheckerListener = answerCheckerListener;
        

    }

    public void setLessonsNumber(String text) {
        this.lessonsNumber = text;
    }

    public void setColorsForAnswers() {

    }

    public void setButtonNextLessonListener(NextLessonListener nextLessonListener) {
        this.nextLessonListener = nextLessonListener;
    }
}


























































// WAS OK IN order to clear all FIELDS

//
//Enumeration<AbstractButton> elementsWest = 
//                        panelWest.getAnswerGroup().getElements();
//                Enumeration<AbstractButton> elementsCenter = 
//                        panelCenter.getAnswerGroup().getElements();
//                Enumeration<AbstractButton> elementsEast = 
//                        panelEast.getAnswerGroup().getElements();
//
//                panelWest.getButton(elementsWest.nextElement()
//                        .getActionCommand()).setBackground(new Color(174,217,246));
//                panelWest.getButton(elementsWest.nextElement()
//                        .getActionCommand()).setBackground(new Color(174,217,246));
//                panelWest.getButton(elementsWest.nextElement()
//                        .getActionCommand()).setBackground(new Color(174,217,246));
//                
//                panelCenter.getButton(elementsCenter.nextElement()
//                        .getActionCommand()).setBackground(new Color(174,217,246));
//                panelCenter.getButton(elementsCenter.nextElement()
//                        .getActionCommand()).setBackground(new Color(174,217,246));
//                panelCenter.getButton(elementsCenter.nextElement()
//                        .getActionCommand()).setBackground(new Color(174,217,246));
//                
//                panelEast.getButton(elementsEast.nextElement()
//                        .getActionCommand()).setBackground(new Color(174,217,246));
//                panelEast.getButton(elementsEast.nextElement()
//                        .getActionCommand()).setBackground(new Color(174,217,246));
//                panelEast.getButton(elementsEast.nextElement()
//                        .getActionCommand()).setBackground(new Color(174,217,246));



// TEST
                
//                int goodAnswersCounter = 0;
//                
//                if(answer_1.equals(answerToQuestion[0])){
//                    panelWest.getButton(answer_1).setBackground(new Color(162, 235, 94));
//                    goodAnswersCounter++;
//                } else {
//                    panelWest.getButton(answer_1).setBackground(new Color(224, 117, 81));
//                }
//                
//                if(answer_2.equals(answerToQuestion[1])){
//                    panelCenter.getButton(answer_2).setBackground(new Color(162, 235, 94));
//                    goodAnswersCounter++;
//                } else {
//                    panelCenter.getButton(answer_2).setBackground(new Color(224, 117, 81));
//                }
//                
//                if(answer_3.equals(answerToQuestion[2])){
//                    panelEast.getButton(answer_3).setBackground(new Color(162, 235, 94));
//                    goodAnswersCounter++;
//                } else {
//                    panelEast.getButton(answer_3).setBackground(new Color(224, 117, 81));
//                }
//                
//                if(goodAnswersCounter == 3) {
//                    buttonNextLesson.setBackground(new Color(162, 235, 94));
//                }






