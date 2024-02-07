package quizzapp;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Result implements Serializable {

    private static final String PATH = "results.dat";

    private String quizCode;
    private int userScore;
    private List<String> userAnswers;
    private String student;

    public Result(String quizCode, int userScore, String student, List<String> userAnswers) {
        this.quizCode = quizCode;
        this.userScore = userScore;
        this.student = student;
        this.userAnswers = userAnswers;
    }

    public String getQuizCode() {
        return quizCode;
    }

    public int getUserScore() {
        return userScore;
    }

    public String getStudent() {
        return student;
    }

    public List<String> getUserAnswers() {
        return userAnswers;
    }

    public static void saveResult(Result result) {
        List<Result> results = getAllResults();
        results.add(result);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PATH))) {
            oos.writeObject(results);
            System.out.println("Quiz result saved.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Result> getAllResults() {
        List<Result> results = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PATH))) {
            Object obj = ois.readObject();
            if (obj instanceof List) {
                results = (List<Result>) obj;
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No quiz results found.");
        }

        return results;
    }

    public static int getQuizResult(String quizCode, String studentEmail) {
        List<Result> results = getAllResults();

        for (Result result : results) {
            if (result.getQuizCode().equals(quizCode) && result.getStudent().equals(studentEmail)) {
                return result.getUserScore();
            }
        }

        return -1;
    }

    public static List<Result> getUserResults(String userEmail) {
        List<Result> userResults = new ArrayList<>();

        try {
            List<Result> allResults = getAllResults();

            for (Result result : allResults) {
                if (result.getStudent().equals(userEmail)) {
                    userResults.add(result);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userResults;
    }

    public static List<Result> getResultsByQuizCode(String quizCode) {
        List<Result> allResults = getAllResults();
        System.out.println(allResults.size());
        List<Result> quizResults = new ArrayList<>();
        for (Result result : allResults) {
            System.out.println(result.getQuizCode() + " " + quizCode);
            if (result.getQuizCode().equals(quizCode)) {
                quizResults.add(result);
            }
        }

        return quizResults;
    }

}
