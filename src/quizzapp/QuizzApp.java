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
        User sampleStudent2 = new Student("arber.xhepaliu@gmail.com", "123123", "Arber");

        // Save the user to the file
        User.createUser(sampleStudent);
        User.createUser(sampleStudent2);

        User sampleProf = new Professor("p", "p", "Test Prof");
        // Save the user to the file
        User.createUser(sampleProf);

        System.out.println(Quiz.getAllQuizes().size());
        for (Quiz quiz : Quiz.getAllQuizes()) {
            System.out.println(quiz);
        }
    }
}
