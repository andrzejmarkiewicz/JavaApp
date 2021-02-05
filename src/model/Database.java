/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;



/**
 *
 * @author andrzej
 */
public class Database {

    // this needs to be saved
    // saved will be only the status of Lesson Object
    // if the lesson is unlocked
    // etc.
    private Map<String, Lesson> mapTextToLesson;
    
    

    public Database() {
        
//        System.out.println("START TIME");
//        long startTime = System.currentTimeMillis();
//        Lesson lesson1 = new Lesson("Lektion 1");
//        Lesson lesson2 = new Lesson("Lektion 2");
//        mapTextToLesson.put("Lektion 1", lesson1);
//        mapTextToLesson.put("Lektion 2", lesson2);
//        long endTime = System.currentTimeMillis();
//        System.out.print("Difference: ");
//        System.out.println(endTime - startTime);
       

        initializeDatabase();
    }
    
    private void initializeDatabase() {
        mapTextToLesson = new HashMap<>();
        LessonNumbered[] values = LessonNumbered.values();
        for(LessonNumbered lesson : values) {
            mapTextToLesson.put
            (lesson.getDescription(), new Lesson(lesson.getDescription()));
        }
        
        // setting first lesson as is open for view.
//        mapTextToLesson.get("Lektion 1").setOpenForView();
        mapTextToLesson.get("Lektion 1").setUnlocked(); // TEST
        
        
    }
    
    public String getFromDatabase(String key) {
        
        if(mapTextToLesson.containsKey(key)) {
//            System.out.println("--------------------------------");
//            System.out.println("THE LESSON IS ALREADY IN MAP!!!!!!");
//            System.out.println("--------------------------------");
            return mapTextToLesson.get(key).getLessonsText();
        }
        
        
        Lesson lesson = new Lesson(key);
        mapTextToLesson.put(key, lesson);
        
//        System.out.println("-----------------------------");
//        System.out.println("THIS LESSON IS NOT IN THE MAP YET!!!!!!!");
//        System.out.println("----------------------------");
        
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
    

    private String retrieveTextFromFile(String text) throws FileNotFoundException {
        
        File file = new File(text);
        StringBuilder outcomingTex = new StringBuilder();
        
        
        BufferedReader br = new BufferedReader(new FileReader(text));
        try {
            String line = br.readLine();

            while (line != null) {
                outcomingTex.append(line);
                outcomingTex.append(System.lineSeparator());
                line = br.readLine();
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
        return outcomingTex.toString();
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
            
        } catch (ClassNotFoundException ex) {

        } catch (IOException ex) {

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
        // text -> check if previous unlocked

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
