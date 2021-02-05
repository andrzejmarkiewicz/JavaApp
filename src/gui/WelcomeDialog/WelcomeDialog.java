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
//    private NewJDialog dialog;
    private JLabel welcomeLabel;
    private JLabel welcomeLabelIcon;
    

    public WelcomeDialog() {
//        setUndecorated(true);
        setDialogSizeAndLocation();
        
        setLayout(new BorderLayout());
        
        buttonOpen = new JButton("Fortfahren");
        buttonClose = new JButton("Cancel");
        welcomeLabel = new JLabel("WELCOME");
        welcomeLabelIcon = new JLabel();
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        welcomeLabelIcon.setIcon(new ImageIcon(getClass().getResource("/icons/intro3.png")));
        
//        dialog = new NewJDialog(new JFrame(), true);
        
 
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(new Color(175, 226, 189));
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(175, 226, 189));
        
        
        buttonOpen.setBorderPainted(true);
        buttonOpen.setBackground(new Color(86, 157, 219));
        buttonOpen.setFocusPainted(false);
        buttonOpen.setPreferredSize(new Dimension(140, 25));

        
        buttonClose.setBorderPainted(true);
        buttonClose.setBackground(new Color(86, 157, 219));
        buttonClose.setFocusPainted(false);
        buttonClose.setPreferredSize(new Dimension(140, 25));

        
        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        
        buttonPanel.add(buttonOpen);
        buttonPanel.add(buttonClose);
        
        mainPanel.add(welcomeLabel, BorderLayout.CENTER);
        mainPanel.add(welcomeLabelIcon, BorderLayout.SOUTH);



        int space = 5;
        Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
        mainPanel.setBorder(BorderFactory.createBevelBorder(10, Color.lightGray, Color.BLACK));
        
        
        
        buttonOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setVisible(false);
//                dialog.setVisible(true);
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

    private void setDialogSizeAndLocation() {
        
        this.setSize(400, 400);
        
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        
        setLocation((screenWidth - this.getWidth())/2,
                (screenHeight - this.getHeight())/2);
        
        setVisible(true);
        
        
        
        
    }
    
    
    
}
