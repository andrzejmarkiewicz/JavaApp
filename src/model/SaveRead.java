/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author andrzej
 */
public class SaveRead implements Serializable {
    
    private static final String PATH = "/home/andrzej/NetBeansProjects/"
            + "javaApp/javaDeutsch/src/resourcesFiles/saveFile.txt";
    // "/home/andrzej/NetBeansProjects/javaApp/javaDeutsch/src/resourcesFiles"

    public void saveFile(Map<String, Lesson> users)
            throws IOException
    {
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(PATH))) {
            os.writeObject(users);
        }
    }

    public Map<String, Lesson> readFile() throws ClassNotFoundException, IOException
    {
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(PATH))) {
            return (Map<String, Lesson>) is.readObject();
        }
    }
    
}
