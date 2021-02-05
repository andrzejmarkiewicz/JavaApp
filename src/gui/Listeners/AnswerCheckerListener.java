/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.Listeners;

/**
 *
 * @author andrzej
 */
public interface AnswerCheckerListener {
    
    public Boolean[] checkAnswerButtonPressed(String[] answers, String lessonsNumber);
}
