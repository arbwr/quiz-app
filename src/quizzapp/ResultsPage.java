package quizzapp;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ResultsPage extends javax.swing.JFrame {

    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JButton back_btn;

    User currentuser;

    public ResultsPage(User user) {
        currentuser = user;
        initComponents();
        List<Result> userResults = Result.getUserResults(user.getEmail());

        // Set FlowLayout for mainPanel
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
            mainPanel.setAlignmentX(Component.LEFT_ALIGNMENT); 

        // Add header panel
        JPanel headerPanel = createHeaderPanel();
        mainPanel.add(headerPanel);

        for (Result result : userResults) {
            JPanel resultPanel = createResultPanel(result);
            mainPanel.add(resultPanel);
        }

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private ResultsPage() {
        throw new UnsupportedOperationException("Not supported.");
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.X_AXIS));

        JLabel codeLabel = new JLabel("Code");
        JLabel nameLabel = new JLabel("Quiz Name");
        JLabel resultLabel = new JLabel("Score");

        // Set smaller padding between labels
        codeLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 10, 30, 30));
        nameLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 10, 30, 30));
        resultLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 10, 30, 30));

        headerPanel.add(codeLabel);
        headerPanel.add(nameLabel);
        headerPanel.add(resultLabel);

        return headerPanel;
    }

    private JPanel createResultPanel(Result result) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        Quiz quiz = Quiz.getQuizByCode(result.getQuizCode());
        JLabel codeLabel = new JLabel(result.getQuizCode());
        JLabel nameLabel = new JLabel(quiz.getName());
        JLabel resultLabel = new JLabel(result.getUserScore() + "/" + quiz.getQuestionList().size());
        resultLabel.setForeground(Color.BLUE);

        codeLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 20, 10, 30));
        nameLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 20, 10, 30));
        resultLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 20, 10, 30));

        panel.add(codeLabel);
        panel.add(nameLabel);
        panel.add(resultLabel);

        return panel;
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        mainPanel = new javax.swing.JPanel();
        back_btn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(659, 390));

        jLabel1.setFont(new java.awt.Font("Microsoft JhengHei UI Light", 1, 18));
        jLabel1.setForeground(new java.awt.Color(0, 5, 144));
        jLabel1.setText("Quiz Results");

        back_btn.setText("Back");
        back_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                back_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(back_btn)))
                                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(back_btn))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE) // Increased scroll pane size
                                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void back_btnActionPerformed(java.awt.event.ActionEvent evt) {
        JFrame studentDashboard = new Dashboard(currentuser);
        this.setVisible(false);
        studentDashboard.setVisible(true);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new ResultsPage().setVisible(true);
        });
    }
}