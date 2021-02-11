/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Serializable;


/**
 *
 * @author andrzej
 */
public class Lesson implements Serializable{
    private String lessonsText;
    private String questionAndAnswers;
    
    private String[] questions;
    private String[] possibleAnswers;
    private String[] rightAnswers;
    
    private boolean unlocked; // by default - false    
    
    private String lesson_nr;
    
    private final String PATH_BEGINNING = "/home/andrzej/NetBeansProjects/"
            + "javaApp/javaDeutsch/src/resourcesFiles/";

    public Lesson(String lesson_nr) {
        this.lesson_nr = lesson_nr;
        this.unlocked = false;
        
        initializeLessonText();
        initializeLessonQuestionAndAnswers();
        separateQuestionsFromAnswers(); 
    }
    
    private void initializeLessonText() {
        String pathNameForText = preparePathNameForText();
        
        try {
            lessonsText = retrieveTextFromFile(pathNameForText);
        } catch (Exception e) {
            System.out.println("FILE NOT FOUND");
        } 
    }
    
    private void initializeLessonQuestionAndAnswers() {
        String pathNameForQuestion = preparePathNameForQuestion();
        try {
            questionAndAnswers = retrieveTextFromFile(pathNameForQuestion);
        } catch (Exception e) {
            System.out.println("FILE NOT FOUND");
        }
    }
    
    private String preparePathNameForText() {
        StringBuilder builder = preparePathName();
        builder.append("Text.txt");
        
        return builder.toString();
    }
    
    private String preparePathNameForQuestion() {
        StringBuilder builder = preparePathName();
        builder.append("quest.txt");
        return builder.toString();
    }
    
    private StringBuilder preparePathName() {
        StringBuilder builder = new StringBuilder();
        builder.append(PATH_BEGINNING);
        
        String[] parts = lesson_nr.split(" ");
        String lessonsNumber = parts[1];
        
        builder.append(lessonsNumber);
        builder.append("_Lektion_");
        return builder;
    }
    
    private String retrieveTextFromFile(String text) throws FileNotFoundException {
        
        StringBuilder outcomingText = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(text))){
            String line = br.readLine();

            while (line != null) {
                outcomingText.append(line);
                outcomingText.append(System.lineSeparator());
                line = br.readLine();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return outcomingText.toString();
    }
    
    public String getLessonsText() {
        return lessonsText;
    }
    
    public String getQuestionAndAnswers() {
        return questionAndAnswers;
    }

    private void separateQuestionsFromAnswers() {
        // separate question and possible answers from right answers
        String[] separatorFirstStage = questionAndAnswers.split(">");
        
        // The right answers taken into Array of Strings -> 3 Elements
        String[] temp = separatorFirstStage[1].split(",");
        rightAnswers = new String[3];
        rightAnswers[0] = temp[0].trim();
        rightAnswers[1] = temp[1].trim();
        rightAnswers[2] = temp[2].trim();
        
        // The question and possible answers
        String[] separatorSecondStage = separatorFirstStage[0].split("\n");
        questions = new String[3];
        questions[0] = separatorSecondStage[0].trim();
        questions[1] = separatorSecondStage[1].trim();
        questions[2] = separatorSecondStage[2].trim();
        
        possibleAnswers = new String[3];
        possibleAnswers[0] = separatorSecondStage[3].trim();
        possibleAnswers[1] = separatorSecondStage[4].trim();
        possibleAnswers[2] = separatorSecondStage[5].trim();   
    }
    
    public String[] getQuestions() {
        return questions;
    }

    public String[] getPossibleAnswers() {
        return possibleAnswers;
    }

    public String[] getRightAnswers() {
        return rightAnswers;
    }
    
    public boolean isUnlocked() {
        return unlocked;
    }
    
    public void setUnlocked() {
        this.unlocked = true;
    }

    public Boolean[] checkAnswers(String[] answers) {
        Boolean[] answersResult = new Boolean[3];
        
        for(int i=0; i<answers.length; i++) {
            if(answers[i].equals(rightAnswers[i])) {
                answersResult[i] = true;
            } else {
                answersResult[i] = false;
            }
            
        }
        return answersResult;
    }
}
