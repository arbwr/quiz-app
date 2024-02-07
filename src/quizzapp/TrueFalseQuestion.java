package quizzapp;

public class TrueFalseQuestion extends Question {

    private int correctAnswer;

    public TrueFalseQuestion(String questionText, int correctAnswer) {
        super(questionText);
        this.correctAnswer = correctAnswer;
    }

    public boolean isCorrectAnswer(int answerIndex) {
        assert answerIndex >= 0 : "Answer index must be non-negative";
        return answerIndex == correctAnswer;
    }

    /* Overridden method to display a true/false question */
    @Override
    void displayQuestion() {
        System.out.println(getQuestionText());
        System.out.println("True or False?");
    }
}
