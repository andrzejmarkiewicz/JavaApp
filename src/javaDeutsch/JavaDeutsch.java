/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javadeutsch;

import gui.WelcomeDialog.WelcomeDialog;
import java.awt.EventQueue;

/**
 *
 * @author andrzej
 */
public class JavaDeutsch {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        EventQueue.invokeLater(() ->
        {
            var dialogNew = new WelcomeDialog();        
        });
        
        
        
    }
    
}
