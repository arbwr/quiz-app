package quizzapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class TakeQuizView extends JFrame {

    private int userScore;
    private User currentuser;
    private Timer timer;
    private int duration;

    private Quiz quiz;
    private List<Question> questions;
    private int currentQuestionIndex;
    private List<String> userAnswers;
    private List<ButtonGroup> optionGroups;
    private JLabel countdownLabel;

    private JLabel progressLabel = new JLabel();
    private JTextArea questionText;
    private JButton nextButton;
    private JButton prevButton;
    private JButton skipButton;
    private JButton submitButton;
    private JPanel questionPanel;
    private JPanel optionsPanel;

    public TakeQuizView(Quiz quiz, User user) {
        this.quiz = quiz;
        this.questions = quiz.getQuestionList();
        this.currentQuestionIndex = 0;
        this.userAnswers = new ArrayList<>();
        this.optionGroups = new ArrayList<>();
        this.userScore = 0;
        currentuser = user;

        duration = quiz.getDuration() * 60;

        if (duration > 0) {
            timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    duration--;
                    updateCountdownLabel();
                    if (duration == 0) {
                        handleTimeout();
                    }
                }
            });
            timer.start();
        }

        initComponents();
        displayCurrentQuestion();
    }

    private void initComponents() {
        setTitle("Take Quiz");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 250);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        if (duration > 0) {
            countdownLabel = new JLabel("Time Remaining: " + duration / 60 + " min");
            mainPanel.add(countdownLabel);
            countdownLabel.setBounds(0, 10, 100, 20);
        }

        progressLabel = new JLabel("Question " + (currentQuestionIndex + 1) + " of " + quiz.getQuestionList().size() + "\n\n");
        mainPanel.add(progressLabel);
        progressLabel.setBounds(0, 10, 100, 20);

        questionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        questionPanel.setName("questionPanel");
        questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS));
        mainPanel.add(questionPanel);
        questionPanel.setBounds(0, 40, 450, 20);

        optionsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        optionsPanel.setName("optionsPanel");
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        mainPanel.add(optionsPanel);

        // Add some vertical space between the question and options
        mainPanel.add(Box.createVerticalStrut(10));

        nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleNextButton();
            }
        });

        prevButton = new JButton("Previous");
        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handlePrevButton();
            }
        });

        skipButton = new JButton("Skip");
        skipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSkipButton();
            }
        });

        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSubmitButton();
            }
        });

        mainPanel.add(Box.createVerticalStrut(10));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(prevButton);
        buttonPanel.add(nextButton);
        buttonPanel.add(skipButton);
        buttonPanel.add(submitButton);

        // Align buttons to the bottom
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(buttonPanel);

        add(mainPanel);
    }

    private void displayCurrentQuestion() {
        progressLabel.setText("Question " + (currentQuestionIndex + 1) + " of " + quiz.getQuestionList().size());
        if (currentQuestionIndex >= 0 && currentQuestionIndex < quiz.getQuestionList().size()) {
            Question currentQuestion = quiz.getQuestionList().get(currentQuestionIndex);

            questionPanel.removeAll();
            questionPanel.add(new JLabel(currentQuestion.getQuestionText()));

            optionsPanel.removeAll();

            if (currentQuestion instanceof MultipleChoiceQuestion) {
                displayMultipleChoiceOptions((MultipleChoiceQuestion) currentQuestion, optionsPanel);
            } else if (currentQuestion instanceof TrueFalseQuestion) {
                displayTrueFalseOptions((TrueFalseQuestion) currentQuestion, optionsPanel);
            }

            revalidate();
            repaint();
        }
    }

    private void displayMultipleChoiceOptions(MultipleChoiceQuestion question, JPanel optionsPanel) {
        ButtonGroup optionGroup = new ButtonGroup();
        optionGroups.add(optionGroup);

        for (int i = 0; i < question.getOptions().size(); i++) {
            JRadioButton radioButton = new JRadioButton(question.getOptions().get(i));
            optionGroup.add(radioButton);
            optionsPanel.add(radioButton);
        }
    }

    private void displayTrueFalseOptions(TrueFalseQuestion question, JPanel optionsPanel) {
        ButtonGroup optionGroup = new ButtonGroup();
        optionGroups.add(optionGroup);

        JRadioButton trueButton = new JRadioButton("True");
        JRadioButton falseButton = new JRadioButton("False");

        optionGroup.add(trueButton);
        optionGroup.add(falseButton);

        optionsPanel.add(trueButton);
        optionsPanel.add(falseButton);
    }

    private void handleNextButton() {
        if (currentQuestionIndex < questions.size() - 1) {
            saveUserAnswer();
            currentQuestionIndex++;
            displayCurrentQuestion();
        }
    }

    private void handlePrevButton() {
        if (currentQuestionIndex > 0) {
            saveUserAnswer();
            currentQuestionIndex--;
            displayCurrentQuestion();
        } else {
            JOptionPane.showMessageDialog(this, "This is the first question.", "Start of Quiz", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void handleSkipButton() {
        saveUserAnswer();
        currentQuestionIndex++;
        displayCurrentQuestion();
    }

    private void handleSubmitButton() {
        markUnansweredQuestions();
        saveUserAnswer();
        calculateAndDisplayScore();
        saveResults();
        closeQuiz();
    }

    private void saveUserAnswer() {
        if (currentQuestionIndex >= 0 && currentQuestionIndex < quiz.getQuestionList().size()) {
            ButtonGroup optionGroup = optionGroups.get(currentQuestionIndex);
            Enumeration<AbstractButton> buttons = optionGroup.getElements();
            int selectedIndex = 0;
            boolean found = false;
            while (buttons.hasMoreElements()) {
                AbstractButton button = buttons.nextElement();
                if (button.isSelected()) {
                    System.out.println(button.getText());
                    userAnswers.add(String.valueOf(selectedIndex));
                    found = true;
                    break;
                }
                selectedIndex++;
            }

            if (!found) {
                userAnswers.add(String.valueOf("-44"));
            }
        }
    }

    private void markUnansweredQuestions() {
        for (int i = userAnswers.size(); i < quiz.getQuestionList().size(); i++) {
            userAnswers.add(String.valueOf("-44"));
        }
    }

    private void calculateAndDisplayScore() {
        int score = 0;

        for (int i = 0; i < quiz.getQuestionList().size(); i++) {
            Question question = quiz.getQuestionList().get(i);
            String userAnswerIndexStr = userAnswers.get(i);

            if (userAnswerIndexStr != null && !userAnswerIndexStr.isEmpty()) {
                int userAnswerIndex = Integer.parseInt(userAnswerIndexStr);
                if (question.isCorrectAnswer(userAnswerIndex)) {
                    score++;
                }
            }
        }

        userScore = score;
        JOptionPane.showMessageDialog(this, "Your score: " + score + " out of " + quiz.getQuestionList().size());
    }

    private void saveResults() {
        String quizCode = quiz.getCode();

        Result result = new Result(quizCode, userScore, currentuser.getEmail(), userAnswers);
        Result.saveResult(result);
    }

    private void closeQuiz() {
        JFrame studentdashboard = new Dashboard(currentuser);
        this.setVisible(false);
        studentdashboard.setVisible(true);
    }

    private void handleTimeout() {
        JOptionPane.showMessageDialog(this, "Time's up! Quiz will be submitted.", "Time's Up", JOptionPane.INFORMATION_MESSAGE);
        handleSubmitButton();
        timer.stop();
        closeQuiz();
    }

    private void updateCountdownLabel() {
        int minutes = duration / 60;
        countdownLabel.setText("Time Remaining: " + (minutes + 1) + " min");
    }

    public static void main(String[] args) {
        Quiz sampleQuiz = new Quiz();
        Student user = new Student("test", "test", "test");

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TakeQuizView(sampleQuiz, user).setVisible(true);
            }
        });
    }
}
