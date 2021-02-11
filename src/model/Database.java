/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;



/**
 *
 * @author andrzej
 */
public class Database {

    private Map<String, Lesson> mapTextToLesson;

    public Database() {
        initializeDatabase();
    }
    
    private void initializeDatabase() {
        mapTextToLesson = new HashMap<>();
        LessonNumbered[] values = LessonNumbered.values();
        
        for(LessonNumbered lesson : values) {
            mapTextToLesson.put
            (lesson.getDescription(), new Lesson(lesson.getDescription()));
        }
        
        mapTextToLesson.get("Lektion 1").setUnlocked();

    }
    
    public String getFromDatabase(String key) {
        
        if(mapTextToLesson.containsKey(key)) {
            return mapTextToLesson.get(key).getLessonsText();
        }
        
        Lesson lesson = new Lesson(key);
        mapTextToLesson.put(key, lesson);
        
        return mapTextToLesson.get(key).getLessonsText();
    }
    
    public String getTextFromDatabase(String key) {
        
        return mapTextToLesson.get(key).getLessonsText();
    }
    
    
    public String getQuestionsFromDatabase(String key) {
        return mapTextToLesson.get(key).getQuestionAndAnswers();
    }
    
    
    public String[] getQuestionFromTheDatabase(String key) {
        
        return mapTextToLesson.get(key).getQuestions();
    }
    
    public String[] getPossibleAnswers(String key) {
        return mapTextToLesson.get(key).getPossibleAnswers();
    }
    
    public String[] getRightAnswers(String key) {
        return mapTextToLesson.get(key).getRightAnswers();
    }

    public Map<String, Boolean> getLessonsStatus() {
        Map<String, Boolean> mapStringToBoolean = new HashMap<>();
        
        for (Map.Entry<String, Lesson> entry : mapTextToLesson.entrySet()) {
            String key = entry.getKey();
            Lesson value = entry.getValue();
            mapStringToBoolean.put((key), value.isUnlocked());
        }
        return mapStringToBoolean;
    }
    
    public void saveToFile() {
        
        SaveRead i = new SaveRead();
        try {
//            System.out.println("SAVING............................");
            i.saveFile(mapTextToLesson);
        } catch (IOException ex) {

        }
    }
    
    public void loadFromFile() {
        
        SaveRead i = new SaveRead();
        
        try {
            long timeStart = System.currentTimeMillis();
            mapTextToLesson = i.readFile();
            long timeEnd = System.currentTimeMillis();
            
        } catch (Exception e) {

        } 
    }

    public void setLessonStatus(String text) {
        Lesson lesson = mapTextToLesson.get(text);
        lesson.setUnlocked();
    }
    
    public boolean getLessonStatus(String text) {
        return mapTextToLesson.get(text).isUnlocked();
    }

    public Boolean[] checkAnswers(String[] answers, String lessonNumber) {
        return mapTextToLesson.get(lessonNumber).checkAnswers(answers);
    }
    
    public boolean checkIfUnlocked(String text) {
        String[] parts = text.split(" ");

        if(parts[1].equals("1")) return true;
        
        int lessonNumber = Integer.parseInt(parts[1]);
      
        StringBuilder builder = new StringBuilder();
        builder.append("Lektion ");
        int lessonNumberLessOne = lessonNumber-1;
        String lessonString = String.valueOf(lessonNumberLessOne);
        builder.append(lessonString);
        
        if(mapTextToLesson.get(builder.toString()).isUnlocked()) {
            return true;
        }
        
        return false;
    }
}
