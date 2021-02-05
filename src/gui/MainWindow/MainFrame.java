/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.MainWindow;

import gui.MainWindow.SidePanel.SidePanel;
import gui.MainWindow.MainPanel.MainPanel;
import gui.Listeners.AnswerCheckerListener;
import gui.Listeners.ExitButtonListener;
import gui.Listeners.NextLessonListener;
import gui.Listeners.QuestionButtonListener;
import gui.Listeners.SidePanelButtonListener;
import controller.Controller;
import gui.MainWindow.MainPanel.MainPanelToolbar;
import gui.MainWindow.MainPanel.QuestionsPanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author andrzej
 */

public class MainFrame extends JFrame {
    
    private MainPanel mainPanel;
    private SidePanel sidePanel;
    private Toolbar toolbar;
    private Controller controller;

    public MainFrame() {
        super("LERNEN");
//        setUndecorated(true);
        
        setLayout(new BorderLayout());
        mainPanel = new MainPanel();
        sidePanel = new SidePanel();
        
        controller = new Controller();
        toolbar = new Toolbar();
        
        add(mainPanel, BorderLayout.CENTER);
        add(sidePanel, BorderLayout.WEST);
        add(toolbar, BorderLayout.NORTH);
        
        
        setFrameSizeAndLocation();
        
//        loadFromDatabaseIsOpenForView();
        
        loadFromDatabaseTextAndIconsAndUpdateGui();
        
        
        sidePanel.setButtonListener(new SidePanelButtonListener() {
            @Override
            public void buttonPressed(String text) {
                JLabel label = sidePanel.getIconFromButtonText(text);

                if(controller.checkIfUnlocked(text)){
                    // getting text from database and setting it to main panel
                    String incomingText = controller.getFromDb(text);
                    mainPanel.clearTextArea();
                    mainPanel.setTextForMainPanel(incomingText);

                    // changing card to TEXT    
                    CardLayout cl = (CardLayout)(mainPanel.getCardPanel().getLayout());
                    cl.show(mainPanel.getCardPanel(), "text");

                    // updating Toolbar in main Panel -> Lektion 1, 2, 3...
                    MainPanelToolbar toolbarForMainPanel = mainPanel.getToolbar();
                    toolbarForMainPanel.setTextForLabel(text);

                    // getting questions from database
                    String[] questions = controller.getQuestionsArrayFromDatabase(text);
                    String[] possibleAnswers = controller.getPossibleAnswersFromDatabase(text);
                    String[] rightAnswers = controller.getRightAnswersFromDatabase(text);

                    // transferring questions to questions panel
                    QuestionsPanel panel = mainPanel.getQuestionsPanel();
                    panel.setTextForQuestions(questions);
                    panel.setTextForAnswers(possibleAnswers);
                    panel.setAnswersForQuestion(rightAnswers);
                    
                    // setting lessons number in order to check later the answers
                    panel.setLessonsNumber(text);
                }
            }
        });
        
        
        mainPanel.setQuestionButtonListener(new QuestionButtonListener() {
            @Override
            public void questionButtonPressed(String text) {
                CardLayout cl = (CardLayout)(mainPanel.getCardPanel().getLayout());
                cl.show(mainPanel.getCardPanel(), "question");
                
                mainPanel.getQuestionsPanel().setIsNotAllowableToGoToNextLesson();
            }
        });
        
        
        toolbar.setExitButtonListener(new ExitButtonListener() {
            @Override
            public void exitButtonPressed() {
//                System.out.println("SAVING.......................");
                controller.saveToDatabase();
                System.exit(0);
            }
        });
        
        mainPanel.getQuestionsPanel().setAnswerCheckerListener(new AnswerCheckerListener() {
            @Override
            public Boolean[] checkAnswerButtonPressed(String[] answers, String lessonsNumber) {
                Boolean[] checkAnswers = controller.checkAnswers(answers, lessonsNumber);
                return checkAnswers;
            }
        });
        
        
        mainPanel.getQuestionsPanel().setButtonNextLessonListener(new NextLessonListener() {
            @Override
            public void nextLessonButtonPressed(String lessonNumber) {
                controller.setLessonStatus(lessonNumber);   // unlocking Lesson

                // UPDATE ICON
                Map<String, Boolean> mapLessonNumberToLessonStatus = controller.getMapLessonNumberToLessonStatus();
                sidePanel.setMapLessonNumberToLessonStatus(mapLessonNumberToLessonStatus);
                sidePanel.checkLessonStatusAndSetIcon(); // update Icon
                String nextLesson = prepareStringNextLesson(lessonNumber);
                
                // getting text from database and setting it to main panel
                String incomingText = controller.getFromDb(nextLesson);
                mainPanel.clearTextArea();
                mainPanel.setTextForMainPanel(incomingText);

                // changing card to TEXT
                CardLayout cl = (CardLayout)(mainPanel.getCardPanel().getLayout());
                cl.show(mainPanel.getCardPanel(), "text");
                
                // updating Toolbar in main Panel -> Lektion 1, 2, 3...
                MainPanelToolbar toolbarForMainPanel = mainPanel.getToolbar();
                toolbarForMainPanel.setTextForLabel(nextLesson);

                // getting questions from database
                String[] questions = controller.getQuestionsArrayFromDatabase(nextLesson);
                String[] possibleAnswers = controller.getPossibleAnswersFromDatabase(nextLesson);
                String[] rightAnswers = controller.getRightAnswersFromDatabase(nextLesson);
                
                // transferring questions to questions panel
                QuestionsPanel panel = mainPanel.getQuestionsPanel();
                panel.setTextForQuestions(questions);
                panel.setTextForAnswers(possibleAnswers);
                panel.setAnswersForQuestion(rightAnswers);
                // setting lessons number in order to check later the answers
                panel.setLessonsNumber(nextLesson);
                
            }

            private String prepareStringNextLesson(String lessonNumber) {
//                String test = lessonNumber.substring(lessonNumber.length()-1);
                
                String[] s = lessonNumber.split(" ");
                String test = s[1];
                
                int number = Integer.parseInt(test);
                number++;
                String numberAsString = String.valueOf(number);
                
                StringBuilder builder = new StringBuilder();
                builder.append("Lektion ");
                builder.append(numberAsString);
                String text = builder.toString();
                return text;
            }
            
        });
    }
    
    private void setFrameSizeAndLocation() {
        setSize(900, 500);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        
        setLocation((screenWidth - this.getWidth())/2,
                (screenHeight - this.getHeight())/2);
        
        setVisible(true);        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }   

    private void loadFromDatabaseTextAndIconsAndUpdateGui() {
        controller.loadFromDatabase();
        // based on this values the icon will be displayed
        Map<String, Boolean> mapLessonNumberToLessonStatus = controller.getMapLessonNumberToLessonStatus();

        sidePanel.setMapLessonNumberToLessonStatus(mapLessonNumberToLessonStatus);
        sidePanel.checkLessonStatusAndSetIcon();
    }
}
