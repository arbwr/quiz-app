package quizzapp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Quiz implements Serializable {

    private static final String PATH = "quizzes.ser";
    private static final long serialVersionUID = 4773185784453667846L;

    private String name;
    private List<Question> questionList;
    private int duration; // in minutes
    private String code;
    private String createdBy;
    private Date createdAt;
    private String subject;

    public Quiz(String name, int duration, List<Question> questionList, String createdBy) {
        this.name = name;
        this.duration = duration;
        this.code = generateUniqueCode();
        this.questionList = questionList;
        this.createdBy = createdBy;
        this.createdAt = new Date();
    }

    public Quiz() {
        this.code = generateUniqueCode();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getDuration() {
        return duration;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void addQuestion(Question question) {
        if (questionList == null) {
            questionList = new ArrayList<>();
        }
        questionList.add(question);
    }

    public void removeQuestion(Question question) {
        if (questionList != null) {
            questionList.remove(question);
        }
    }

    public static List<Quiz> getAllQuizes() {
        List<Quiz> quizzes = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PATH))) {
            quizzes = (List<Quiz>) ois.readObject();
        } catch (FileNotFoundException e) {
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e);
        }

        return quizzes;
    }

    public static void createQuiz(Quiz quiz) {
        List<Quiz> quizzes = getAllQuizes();
        quizzes.add(quiz);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PATH))) {
            oos.writeObject(quizzes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String generateUniqueCode() {
        return UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    public static Quiz searchQuizByCode(String code) {
        List<Quiz> quizzes = Quiz.getAllQuizes();
        for (Quiz quiz : quizzes) {
            if (quiz.getCode().equalsIgnoreCase(code)) {
                return quiz;
            }
        }
        return null;
    }

    public static List<Quiz> searchQuizzesBySubject(String subject) {
        List<Quiz> quizzes = getAllQuizes();
        List<Quiz> matchingQuizzes = new ArrayList<>();
        for (Quiz quiz : quizzes) {
            if (quiz.getSubject() != null && quiz.getSubject().equalsIgnoreCase(subject)) {
                matchingQuizzes.add(quiz);
            }
        }
        return matchingQuizzes;
    }

    public static Quiz getQuizByCode(String quizCode) {
        try {
            List<Quiz> allQuizzes = getAllQuizes();

            for (Quiz quiz : allQuizzes) {
                if (quiz.getCode().equals(quizCode)) {
                    return quiz;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<Quiz> getQuizzesByProfessor(String professorEmail) {

        List<Quiz> allQuizzes = getAllQuizes();

        List<Quiz> professorQuizzes = new ArrayList<>();
        for (Quiz quiz : allQuizzes) {
            if (quiz.getCreatedBy().equals(professorEmail)) {
                professorQuizzes.add(quiz);
            }
        }

        return professorQuizzes;
    }

    public String displayInfo() {
        StringBuilder info = new StringBuilder();
        info.append("Quiz Information:\n");
        info.append("Name: ").append(name).append("\n");
        info.append("Code: ").append(code).append("\n");
        info.append("Duration: ").append(duration).append(" minutes\n");
        info.append("Created By: ").append(createdBy).append("\n");
        info.append("Created At: ").append(createdAt).append("\n");

        if (questionList != null && !questionList.isEmpty()) {
            info.append("Questions:\n");
            for (Question question : questionList) {
                info.append(question.getQuestionText()).append("\n");
            }
        }

        return info.toString();
    }

    public static double getAverageScore(String code) {
        List<Result> results = Result.getResultsByQuizCode(code);

        if (results.isEmpty()) {
            return 0.0;
        }

        int totalScore = 0;
        for (Result result : results) {
            totalScore += result.getUserScore();
        }

        return (double) totalScore / results.size();
    }

    @Override
    public String toString() {
        return displayInfo();
    }

    Object getQuestions() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
