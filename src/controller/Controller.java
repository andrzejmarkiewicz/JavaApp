/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.Map;
import model.Database;

/**
 *
 * @author andrzej
 */
public class Controller {
    
    private Database db = new Database();

    public String getFromDb(String text) {
        return db.getFromDatabase(text);
    }
    
    public String getQuestionsFromDatabase(String text) {
        return db.getQuestionsFromDatabase(text);
    }
    
    public void saveToDatabase() {
        db.saveToFile();
    }
    
    public void loadFromDatabase() {
        db.loadFromFile();
    }
    
    public String[] getQuestionsArrayFromDatabase(String text) {
        return db.getQuestionFromTheDatabase(text);
    }
    
    public String[] getPossibleAnswersFromDatabase(String text) {
        return db.getPossibleAnswers(text);
    }
    
    public String[] getRightAnswersFromDatabase(String text) {
        return db.getRightAnswers(text);
    }
    
    public Map<String, Boolean> getMapLessonNumberToLessonStatus() {
        return db.getLessonsStatus();
    }

    public void setLessonStatus(String text) {
        db.setLessonStatus(text);
    }
    
    public boolean getLessonStatus(String text) {
        return db.getLessonStatus(text);
        
    }
    
    
    public Boolean[] checkAnswers(String[] answers, String lessonNumber) {
        return db.checkAnswers(answers, lessonNumber);
    }

//    public boolean isOpenForView(String text) {
//        return db.isOpenForView(text);
//    }
//    
    public boolean checkIfUnlocked(String text) {
        return db.checkIfUnlocked(text);
    }
    
}
