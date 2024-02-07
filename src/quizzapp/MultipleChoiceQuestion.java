package quizzapp;

import java.util.List;

public class MultipleChoiceQuestion extends Question {

    private List<String> options;
    private int correctAnswerIndex;

    public MultipleChoiceQuestion(String questionText, List<String> options, int correctAnswerIndex) {
        super(questionText);
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public boolean isCorrectAnswer(int answerIndex) {
        assert answerIndex >= 0 : "Answer index must be non-negative";
        return answerIndex == correctAnswerIndex;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public List<String> getOptions() {
        return options;
    }

    /* Overridden method to display a multiple-choice question */
    @Override
    void displayQuestion() {
        System.out.println(getQuestionText());
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
    }
}
