/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author andrzej
 */
public enum LessonNumbered {
    LESSON_1("Lektion 1"),
    LESSON_2("Lektion 2"),
    LESSON_3("Lektion 3"),
    LESSON_4("Lektion 4"),
    LESSON_5("Lektion 5"),
    LESSON_6("Lektion 6"),
    LESSON_7("Lektion 7"),
    LESSON_8("Lektion 8"),
    LESSON_9("Lektion 9"),
    LESSON_10("Lektion 10"),
    LESSON_11("Lektion 11"),
    LESSON_12("Lektion 12"),
    LESSON_13("Lektion 13"),
    LESSON_14("Lektion 14"),
    LESSON_15("Lektion 15"),
    LESSON_16("Lektion 16"),
    LESSON_17("Lektion 17"),
    LESSON_18("Lektion 18"),
    LESSON_19("Lektion 19"),
    LESSON_20("Lektion 20");
    
    private String lessonsText;

    LessonNumbered(String lessonsText) {
        this.lessonsText = lessonsText;
    }
    
    public String getDescription() {
        return this.lessonsText;
    }
    
//    public LessonNumbered getLesson(String lessonsText) {
//        return LessonNumbered.valueOf(lessonsText);
//    }
}
