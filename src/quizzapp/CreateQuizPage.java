package quizzapp;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class CreateQuizPage extends javax.swing.JFrame {

    private Quiz quiz = new Quiz();
    User currentuser;

    public CreateQuizPage(User user) {
        currentuser = user;
        initComponents(user);
    }

    private CreateQuizPage() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void initComponents(User user) {
        setTitle("Create Quiz");
        setPreferredSize(new java.awt.Dimension(658, 450));
        setMaximumSize(new java.awt.Dimension(658, 450));

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        title_text = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        duration_text = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        add_question_btn = new javax.swing.JButton();
        save_quiz_btn = new javax.swing.JButton();
        back_quiz_btn = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        questionsPanel = new JPanel();
        jLabelSubject = new javax.swing.JLabel();

        JScrollPane scrollPane = new JScrollPane(questionsPanel);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Microsoft JhengHei Light", 1, 18));
        jLabel1.setForeground(new java.awt.Color(0, 5, 144));
        jLabel1.setText("Create Quiz");

        jLabel2.setText("Title");

        jLabel3.setText("Duration (minutes)");

        duration_text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                duration_textActionPerformed(evt);
            }
        });

        jLabelSubject.setText("Subject");
        String[] subjects = {"Java", "Physics", "Math"};
        subjectComboBox = new JComboBox<>(subjects);

        // ... (existing code)
        jLabel4.setText("Questions");

        add_question_btn.setText("Add");
        add_question_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_question_btnActionPerformed(evt);
            }
        });

        save_quiz_btn.setBackground(new java.awt.Color(0, 5, 144));
        save_quiz_btn.setFont(new java.awt.Font("Microsoft JhengHei Light", 0, 14));
        save_quiz_btn.setForeground(new java.awt.Color(255, 255, 255));
        save_quiz_btn.setText("Save");
        save_quiz_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save_quiz_btnActionPerformed(evt);
            }
        });

        back_quiz_btn.setText("Back");
        back_quiz_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                back_quiz_btnActionPerformed(evt, user);
            }
        });

        questionsPanel.setLayout(new BoxLayout(questionsPanel, BoxLayout.Y_AXIS));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(save_quiz_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(back_quiz_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(298, 298, 298))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(jLabel5)
                                                .addGap(315, 315, 315))))
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(31, 31, 31)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jLabel4)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(add_question_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(jLabel1)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel2)
                                                                        .addComponent(title_text, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(90, 90, 90)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel3)
                                                                        .addComponent(duration_text, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(90, 90, 90)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabelSubject)
                                                                        .addComponent(subjectComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        )
                                                        .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(315, 315, 315)
                                                .addComponent(jLabel5)))
                                .addGap(35, 35, 35)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabelSubject)
                                        .addComponent(subjectComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(149, Short.MAX_VALUE))
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(jLabel1)
                                .addGap(33, 33, 33)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabelSubject))
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(duration_text, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(title_text, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(subjectComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(44, 44, 44)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4)
                                        .addComponent(add_question_btn))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(save_quiz_btn)
                                        .addComponent(back_quiz_btn))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabelSubject))
                                .addGap(18, 18, 18))
        );
        pack();
        setLocationRelativeTo(null);
    }

    private void add_question_btnActionPerformed(java.awt.event.ActionEvent evt) {
        String questionText;
        String questionType = (String) JOptionPane.showInputDialog(
                this,
                "Select question type:",
                "Question Type",
                JOptionPane.PLAIN_MESSAGE,
                null,
                new String[]{"Multiple Choice", "True / False"},
                "Multiple Choice"
        );

        if (questionType != null) {
            questionText = JOptionPane.showInputDialog(this, "Enter the question text");

            if (questionText != null && !questionText.isEmpty()) {
                if ("Multiple Choice".equals(questionType)) {
                    try {
                        String optionsLengthStr = JOptionPane.showInputDialog(this, "Enter number of options (between 2 and 5):");

                        if (optionsLengthStr != null) {
                            int optionsLength = Integer.parseInt(optionsLengthStr);

                            if (optionsLength >= 2 && optionsLength <= 5) {
                                List<String> selectedOptions = getMultipleChoiceOptions(optionsLength);

                                if (selectedOptions != null) {
                                    int correctAnswerIndex = getCorrectAnswerIndex(selectedOptions);
                                    System.out.println("correctAnswerIndex: " + correctAnswerIndex);
                                    MultipleChoiceQuestion mcQuestion = new MultipleChoiceQuestion(questionText, selectedOptions, correctAnswerIndex);
                                    quiz.addQuestion(mcQuestion);

                                    // Add a panel for the question in the UI
                                    JPanel questionPanel = new JPanel(new GridBagLayout());
                                    GridBagConstraints gbc = new GridBagConstraints();

                                    JLabel questionLabel = new JLabel(mcQuestion.getQuestionText());
                                    JButton removeButton = new JButton("X");
                                    removeButton.setForeground(Color.RED);

                                    gbc.gridx = 0;
                                    gbc.gridy = 0;
                                    gbc.anchor = GridBagConstraints.WEST;
                                    gbc.insets = new Insets(0, 0, 5, 10);
                                    questionPanel.add(questionLabel, gbc);

                                    gbc.gridx = 1;
                                    gbc.gridy = 0;
                                    gbc.anchor = GridBagConstraints.EAST;
                                    questionPanel.add(removeButton, gbc);

                                    removeButton.addActionListener((ActionEvent e) -> {
                                        quiz.removeQuestion(mcQuestion);
                                        questionsPanel.remove(questionPanel);
                                        questionsPanel.revalidate();
                                        questionsPanel.repaint();
                                        revalidate();
                                        repaint();
                                    });

                                    questionsPanel.add(questionPanel);
                                    questionsPanel.revalidate();
                                    questionsPanel.repaint();
                                    revalidate();
                                    repaint();
                                }
                            } else {
                                JOptionPane.showMessageDialog(this, "Please enter a valid number of options between 2 and 5.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(this, "Please enter a valid integer for the number of options.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    }
                } else if ("True / False".equals(questionType)) {
                    // For True / False questions
                    int correctAnswerIndex = getCorrectAnswerIndex(Arrays.asList("True", "False"));
                    TrueFalseQuestion tfQuestion = new TrueFalseQuestion(questionText, correctAnswerIndex);

                    quiz.addQuestion(tfQuestion);

                    // Add a panel for the question in the UI
                    JPanel questionPanel = new JPanel(new GridBagLayout());
                    GridBagConstraints gbc = new GridBagConstraints();

                    JLabel questionLabel = new JLabel(tfQuestion.getQuestionText());
                    JButton removeButton = new JButton("X");
                    removeButton.setForeground(Color.RED);

                    gbc.gridx = 0;
                    gbc.gridy = 0;
                    gbc.anchor = GridBagConstraints.WEST;
                    gbc.insets = new Insets(0, 0, 5, 10);
                    questionPanel.add(questionLabel, gbc);

                    gbc.gridx = 1;
                    gbc.gridy = 0;
                    gbc.anchor = GridBagConstraints.EAST;
                    questionPanel.add(removeButton, gbc);

                    removeButton.addActionListener((ActionEvent e) -> {
                        quiz.removeQuestion(tfQuestion);
                        questionsPanel.remove(questionPanel);
                        questionsPanel.revalidate();
                        questionsPanel.repaint();
                        revalidate();
                        repaint();
                    });

                    questionsPanel.add(questionPanel);
                    questionsPanel.revalidate();
                    questionsPanel.repaint();
                    revalidate();
                    repaint();
                }
            }
        }
    }

    private void save_quiz_btnActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            // Ensure title is not empty
            String title = title_text.getText().trim();
            if (title.isEmpty()) {
                throw new IllegalArgumentException("Title cannot be empty");
            }

            // Ensure duration is a non-empty string and is a valid integer
            String durationStr = duration_text.getText().trim();
            int duration;
            if (durationStr.isEmpty()) {
                duration = -1;  // Set default value when empty
            } else {
                duration = Integer.parseInt(durationStr);
            }

            // Ensure there is at least one question in the array
            if (quiz.getQuestionList() == null || quiz.getQuestionList().isEmpty()) {
                throw new IllegalArgumentException("At least one question is required");
            }

            String selectedSubject = (String) subjectComboBox.getSelectedItem();
            
            // Set values for the quiz object
            quiz.setName(title);
            quiz.setDuration(duration);
            quiz.setCreatedBy(currentuser.getEmail());
            quiz.setCreatedAt(new Date());
            quiz.setSubject(selectedSubject);

            // Create the quiz
            Quiz.createQuiz(quiz);

            JOptionPane.showMessageDialog(this, "Quiz created successfully with code: " + quiz.getCode());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid duration format. Please enter a valid integer.");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "An unexpected error occurred: " + e.getMessage());
        }
    }

    private void back_quiz_btnActionPerformed(java.awt.event.ActionEvent evt, User user) {
        JFrame professorDashboard = new ProfessorDashboard(user);
        this.setVisible(false);
        professorDashboard.setVisible(true);
    }

    private void duration_textActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private List<String> getMultipleChoiceOptions(int length) {
        List<String> optionsList = new ArrayList<>();

        for (int i = 1; i <= length; i++) {
            String option = JOptionPane.showInputDialog(this, "Enter option " + i + " for the question");

            if (option == null || option.isEmpty()) {
                break;
            }

            optionsList.add(option);
        }

        if (!optionsList.isEmpty()) {
            return optionsList;
        }

        return null;
    }

    private int getCorrectAnswerIndex(List<String> options) {
        Object selectedOption = JOptionPane.showInputDialog(
                this,
                "Select the correct answer",
                "Correct Answer",
                JOptionPane.PLAIN_MESSAGE,
                null,
                options.toArray(new String[0]),
                options.get(0)
        );
       
        if (selectedOption != null) {
            return options.indexOf(selectedOption);
        }
        return -1;
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CreateQuizPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CreateQuizPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CreateQuizPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreateQuizPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            new CreateQuizPage().setVisible(true);
        });
    }

    private javax.swing.JButton add_question_btn;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField duration_text;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelSubject;
    private javax.swing.JPanel questionsPanel;
    private javax.swing.JButton save_quiz_btn;
    private javax.swing.JButton back_quiz_btn;
    private javax.swing.JTextField title_text;
    private JComboBox<String> subjectComboBox;
}
