/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.WelcomeDialog;

import gui.MainWindow.MainFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author andrzej
 */
public class WelcomeDialog extends JDialog {
    
    private JButton buttonOpen;
    private JButton buttonClose;
    private JLabel welcomeLabel;
    private JLabel welcomeLabelIcon;

    public WelcomeDialog() {
        setDialogSizeAndLocation();
        setLayout(new BorderLayout());
        
        buttonOpen = new JButton("Fortfahren");
        buttonClose = new JButton("Cancel");
        welcomeLabel = new JLabel("WELCOME");
        welcomeLabelIcon = new JLabel();
        
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        welcomeLabelIcon.setIcon(new ImageIcon(getClass().getResource("/icons/intro3.png")));
        
        JPanel buttonPanel = new JPanel();
        JPanel mainPanel = new JPanel();
        
        configurePanel(buttonPanel);
        configurePanel(mainPanel);
        
        configureButton(buttonOpen);
        configureButton(buttonClose);
        
        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        buttonPanel.add(buttonOpen);
        buttonPanel.add(buttonClose);
        
        mainPanel.add(welcomeLabel, BorderLayout.CENTER);
        mainPanel.add(welcomeLabelIcon, BorderLayout.SOUTH);

        Border spaceBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        mainPanel.setBorder(BorderFactory.createBevelBorder(10, Color.lightGray, Color.BLACK));
        
        
        
        buttonOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setVisible(false);
                
                EventQueue.invokeLater(() ->
                {
                    var mainFrame = new MainFrame();
                });
            }
        });
        
        buttonClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setVisible(false);
                System.exit(0);
            }
        });
    }
    
    private void configureButton(JButton button) {
        button.setBorderPainted(true);
        button.setBackground(new Color(86, 157, 219));
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(140, 25));
    }
    
    private void configurePanel(JPanel panel) {
        panel.setLayout(new FlowLayout());
        panel.setBackground(new Color(175, 226, 189));
    }

    private void setDialogSizeAndLocation() {
        setSize(400, 400);
        
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        
        setLocation((screenWidth - this.getWidth())/2,
                (screenHeight - this.getHeight())/2);
        
        setVisible(true);   
    }
}
