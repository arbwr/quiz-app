package quizzapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class ProfessorResults extends JFrame {

    private JLabel jLabel1;
    private JPanel mainPanel;
    private JButton back_btn;
    private JTable resultsTable;
    private JButton sortBtn;
    private JButton sortBtnDate;

    private User currentProfessor;

    public ProfessorResults(User user) {
        currentProfessor = user;
        initComponents();
        List<Quiz> professorQuizzes = Quiz.getQuizzesByProfessor(user.getEmail());

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel headerPanel = createHeaderPanel();
        mainPanel.add(headerPanel);

        for (Quiz quiz : professorQuizzes) {
            JPanel quizPanel = createQuizPanel(quiz);
            mainPanel.add(quizPanel);
        }

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.X_AXIS));

        JLabel codeLabel = new JLabel("Quiz Code");
        JLabel nameLabel = new JLabel("Quiz Name");
        JLabel averageScoreLabel = new JLabel("Avg. Score");
        JLabel resultLabel = new JLabel("View Results");

        codeLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 10, 30, 30));
        nameLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 10, 30, 30));
        averageScoreLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 10, 30, 30));
        resultLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 10, 30, 30));

        JButton sortAvgScoreBtn = new JButton("Sort by Avg Score");
        sortAvgScoreBtn.addActionListener(e -> sortQuizzesByHighestAverageScore());

        JButton sortDateCreatedBtn = new JButton("Sort by Date Created");
        sortDateCreatedBtn.addActionListener(e -> sortQuizzesByDateCreated());

        headerPanel.add(codeLabel);
        headerPanel.add(nameLabel);
        headerPanel.add(resultLabel);
        headerPanel.add(sortDateCreatedBtn);
        headerPanel.add(sortAvgScoreBtn);

        return headerPanel;
    }

    /* Selection Sort */
    private void sortQuizzesByDateCreated() {
        List<Quiz> professorQuizzes = Quiz.getQuizzesByProfessor(currentProfessor.getEmail());

        for (int i = 0; i < professorQuizzes.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < professorQuizzes.size(); j++) {
                Date date1 = professorQuizzes.get(j).getCreatedAt();
                Date date2 = professorQuizzes.get(minIndex).getCreatedAt();

                if (date1.before(date2)) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                Quiz temp = professorQuizzes.get(i);
                professorQuizzes.set(i, professorQuizzes.get(minIndex));
                professorQuizzes.set(minIndex, temp);
            }
        }

        updateUIWithSortedQuizzes(professorQuizzes);
    }

    private void updateUIWithSortedQuizzes(List<Quiz> sortedQuizzes) {
        mainPanel.removeAll(); // Clear existing UI components

        JPanel headerPanel = createHeaderPanel();
        mainPanel.add(headerPanel);

        for (Quiz quiz : sortedQuizzes) {
            JPanel quizPanel = createQuizPanel(quiz);
            mainPanel.add(quizPanel);
        }

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    /* Selection Sort */
    private void sortQuizzesByHighestAverageScore() {
        List<Quiz> professorQuizzes = Quiz.getQuizzesByProfessor(currentProfessor.getEmail());

        for (int i = 0; i < professorQuizzes.size() - 1; i++) {
            for (int j = 0; j < professorQuizzes.size() - i - 1; j++) {
                double avgScore1 = Quiz.getAverageScore(professorQuizzes.get(j).getCode());
                double avgScore2 = Quiz.getAverageScore(professorQuizzes.get(j + 1).getCode());

                if (avgScore1 < avgScore2) {
                    Quiz temp = professorQuizzes.get(j);
                    professorQuizzes.set(j, professorQuizzes.get(j + 1));
                    professorQuizzes.set(j + 1, temp);
                }
            }
        }

        updateUIWithSortedQuizzes(professorQuizzes);
    }

    private JPanel createQuizPanel(Quiz quiz) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        JLabel codeLabel = new JLabel(quiz.getCode());
        JLabel nameLabel = new JLabel(quiz.getName());
        double averageScore = Quiz.getAverageScore(quiz.getCode());
        JLabel avgScoreLabel = new JLabel(String.valueOf(averageScore));
        JButton viewResultsButton = new JButton("View Results");

        codeLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 20, 10, 40));
        nameLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 20, 10, 40));
        avgScoreLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 20, 10, 40));
        viewResultsButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 20, 10, 40));

        viewResultsButton.addActionListener(e -> {
            viewQuizResults(quiz);
        });

        panel.add(codeLabel);
        panel.add(nameLabel);
        panel.add(avgScoreLabel);
        panel.add(viewResultsButton);

        return panel;
    }

    private void viewQuizResults(Quiz quiz) {
        List<Result> quizResults = Result.getResultsByQuizCode(quiz.getCode());

        String[] columnNames = {"Quiz Code", "Quiz Name", "Student Email", "Score"};

        Object[][] data = new Object[quizResults.size()][4];
        for (int i = 0; i < quizResults.size(); i++) {
            Result result = quizResults.get(i);
            data[i][0] = quiz.getCode();
            data[i][1] = quiz.getName();
            data[i][2] = result.getStudent();
            data[i][3] = result.getUserScore();
        }

        // Initialize the resultsTable before sorting
        resultsTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(resultsTable);

        JFrame resultsFrame = new JFrame("Quiz Results");
        resultsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        resultsFrame.getContentPane().add(scrollPane);
        resultsFrame.setSize(400, 300);
        resultsFrame.setLocationRelativeTo(this);
        resultsFrame.setVisible(true);
    }

    private void initComponents() {
        setTitle("Professor Quiz Results");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(659, 390));

        jLabel1 = new JLabel();
        mainPanel = new JPanel();
        back_btn = new JButton();

        jLabel1.setFont(new Font("Microsoft JhengHei UI Light", 1, 18));
        jLabel1.setForeground(new Color(0, 5, 144));
        jLabel1.setText("Professor Quiz Results");

        back_btn.setText("Back");
        back_btn.addActionListener(evt -> back_btnActionPerformed(evt));

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(mainPanel, GroupLayout.PREFERRED_SIZE, 620, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(back_btn)))
                                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(back_btn))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(mainPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void back_btnActionPerformed(ActionEvent evt) {
        JFrame professorDashboard = new ProfessorDashboard(currentProfessor);
        this.setVisible(false);
        professorDashboard.setVisible(true);
    }

    public static void main(String args[]) {
        Professor user = new Professor("test", "test", "test");

        java.awt.EventQueue.invokeLater(() -> {
            new ProfessorResults(user).setVisible(true);
        });
    }
}
