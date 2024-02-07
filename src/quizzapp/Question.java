package quizzapp;

import java.io.IOException;
import java.io.Serializable;

abstract public class Question implements Serializable {

    private static final long serialVersionUID = 1L;
    private String questionText;

    public Question(String questionText) {
        this.questionText = questionText;
    }

    public String getQuestionText() {
        return questionText;
    }
    
     private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }

    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    /* Abstract methods */
    abstract void displayQuestion();

    abstract boolean isCorrectAnswer(int answerIndex);
}
