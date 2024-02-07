package quizzapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizzApp {

    public static void main(String[] args) {
        JFrame loginView = new LoginView();
        loginView.setVisible(true);

        User sampleStudent = new Student("s", "s", "Test Student");
        User sampleStudent2 = new Student("s1", "s1", "Test S1");

        // Save the user to the file
        User.createUser(sampleStudent);
        User.createUser(sampleStudent2);

        User sampleProf = new Professor("p", "p", "Test Prof");
        // Save the user to the file
//        User.createUser(sampleProf);

        System.out.println(Quiz.getAllQuizes().size());
        for (Quiz quiz : Quiz.getAllQuizes()) {
            System.out.println(quiz);
        }

        /* SwingUtilities.invokeLater(() -> {
            JFrame mainMenuFrame = createMainMenuFrame();
            mainMenuFrame.setVisible(true);
        });*/
    }

    private static JFrame createMainMenuFrame() {
        JFrame mainMenuFrame = new JFrame("Education Quiz App");
        mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainMenuFrame.setSize(400, 300);
        mainMenuFrame.setLayout(new BorderLayout());

        JPanel mainMenuPanel = new JPanel(new GridLayout(3, 1));

        JButton startQuizButton = new JButton("Start Quiz");
        JButton chooseSubjectsButton = new JButton("Choose Subjects");
        JButton viewProgressButton = new JButton("View Progress");

        startQuizButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainMenuFrame.dispose(); // Close the main menu frame
                startQuiz(); // Call the method to start the quiz
            }
        });

        // Add other action listeners for chooseSubjectsButton and viewProgressButton
        mainMenuPanel.add(startQuizButton);
        mainMenuPanel.add(chooseSubjectsButton);
        mainMenuPanel.add(viewProgressButton);

        mainMenuFrame.add(mainMenuPanel, BorderLayout.CENTER);

        return mainMenuFrame;
    }

    private static void startQuiz() {
        // Logic to start the quiz
        // This is where you will create the quiz screen interface
    }
}
