/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizzapp;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author User
 */
public class StartQuizView extends javax.swing.JFrame {

    /**
     * Creates new form StartQuizView
     */
    User currentuser;

    public StartQuizView(User user) {
        currentuser = user;
        initComponents();
    }

    private StartQuizView() {
        throw new UnsupportedOperationException("Not supported.");
    }

    private void displayQuizzes(List<Quiz> quizzes) {
        questions_panel.removeAll();

        JPanel headerPanel = createHeaderPanel();
        headerPanel.setBounds(0, 10, 450, 30);
        questions_panel.add(headerPanel);

        int yOffset = 20;

        for (Quiz quiz : quizzes) {
            JPanel quizPanel = createQuizPanel(quiz, yOffset);
            quizPanel.setBounds(0, yOffset, 450, yOffset + 30);
            questions_panel.add(quizPanel);
            yOffset += 20;
        }

        questions_panel.setSize(450, yOffset + 10);
        questions_panel.revalidate();
        questions_panel.repaint();
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(null);

        JLabel nameLabel = new JLabel("Name");
        JLabel codeLabel = new JLabel("Code");
        JLabel durationLabel = new JLabel("Duration");
        JLabel startLabel = new JLabel("Start");

        // Set bounds for each label
        nameLabel.setBounds(10, 10, 100, 20);
        codeLabel.setBounds(120, 10, 100, 20);
        durationLabel.setBounds(230, 10, 100, 20);
        startLabel.setBounds(340, 10, 80, 20);

        headerPanel.add(nameLabel);
        headerPanel.add(codeLabel);
        headerPanel.add(durationLabel);
        headerPanel.add(startLabel);

        return headerPanel;
    }

    private JPanel createQuizPanel(Quiz quiz, int yOffset) {
        JPanel panel = new JPanel(null);

        JLabel nameLabel = new JLabel(quiz.getName());
        JLabel codeLabel = new JLabel(quiz.getCode());

        StringBuilder duration = new StringBuilder();
        duration.append(quiz.getDuration());

        if (quiz.getDuration() == -1) {
            duration.replace(0, duration.length(), "Unlimited");
        }

        JLabel durationLabel = new JLabel(duration.toString());

        // Set bounds for each label and button
        nameLabel.setBounds(10, yOffset, 100, 30);
        codeLabel.setBounds(120, yOffset, 100, 30);
        durationLabel.setBounds(230, yOffset, 100, 30);

        panel.add(nameLabel);
        panel.add(codeLabel);
        panel.add(durationLabel);

        // Check if the user has already taken this quiz
        String quizCode = quiz.getCode();
        String userEmail = currentuser.getEmail();

        int userScore = Result.getQuizResult(quizCode, userEmail);

        if (userScore != -1) {
            JLabel resultLabel = new JLabel(userScore + "/" + quiz.getQuestionList().size());
            resultLabel.setForeground(Color.BLUE);
            resultLabel.setBounds(340, yOffset, 120, 30);
            panel.add(resultLabel);

        } else {
            JButton startButton = new JButton("Start");
            startButton.addActionListener(e -> startQuiz(quiz));
            startButton.setBounds(340, yOffset, 80, 30);
            panel.add(startButton);
        }
        return panel;
    }

    private void startQuiz(Quiz quiz) {
        JFrame takeQuizView = new TakeQuizView(quiz, currentuser);
        this.setVisible(false);
        takeQuizView.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        subject_menu = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        id_text = new javax.swing.JTextField();
        search_quiz_btn = new javax.swing.JButton();
        questions_panel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(658, 390));

        jLabel1.setFont(new java.awt.Font("Microsoft JhengHei Light", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 5, 144));
        jLabel1.setText("Quizzes");

        jLabel2.setText("Subject");

        subject_menu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Java", "Math", "Physics" }));
        subject_menu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subject_menuActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("or");

        jLabel4.setText("Code");

        search_quiz_btn.setText("Search");
        search_quiz_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_quiz_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout questions_panelLayout = new javax.swing.GroupLayout(questions_panel);
        questions_panel.setLayout(questions_panelLayout);
        questions_panelLayout.setHorizontalGroup(
            questions_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 583, Short.MAX_VALUE)
        );
        questions_panelLayout.setVerticalGroup(
            questions_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 219, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(subject_menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(136, 136, 136)
                                        .addComponent(jLabel3)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(id_text, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))))
                        .addGap(38, 38, 38)
                        .addComponent(search_quiz_btn))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(questions_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 34, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(subject_menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(id_text, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(search_quiz_btn)))
                .addGap(18, 18, 18)
                .addComponent(questions_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void subject_menuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subject_menuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_subject_menuActionPerformed

    private void search_quiz_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_quiz_btnActionPerformed
        /* Check if Code field has value */
        String code = id_text.getText().trim();

        if (!code.isEmpty()) {
            /* Search By code */
            Quiz foundQuiz = Quiz.searchQuizByCode(code);

            if (foundQuiz != null) {
                List<Quiz> quizzes = new ArrayList<>();
                quizzes.add(foundQuiz);
                displayQuizzes(quizzes);
            } else {
                JOptionPane.showMessageDialog(this, "No quiz found with the provided code.", "Quiz Not Found", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            /* Get value of subject and search by subjects */
            String subject = (String) subject_menu.getSelectedItem();

            if (!subject.isEmpty()) {
                List<Quiz> quizzesBySubject = Quiz.searchQuizzesBySubject(subject);

                if (!quizzesBySubject.isEmpty()) {
                    // Display or process the quizzesBySubject
                    displayQuizzes(quizzesBySubject);
                } else {
                    JOptionPane.showMessageDialog(this, "No quizzes found with the provided subject.", "Quizzes Not Found", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please provide either a code or subject for the search.", "Missing Information", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_search_quiz_btnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */

//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StartQuizView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StartQuizView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StartQuizView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StartQuizView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StartQuizView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField id_text;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel questions_panel;
    private javax.swing.JButton search_quiz_btn;
    private javax.swing.JComboBox<String> subject_menu;
    // End of variables declaration//GEN-END:variables
}
