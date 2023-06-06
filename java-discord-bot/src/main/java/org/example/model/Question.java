package org.example.model;

public class Question {
    private String subject;
    private String question;
    private String A;
    private String B;
    private String C;
    private String correctAnswer;

    public Question(String subject, String question, String a, String b, String c, String correctAnswer) {
        this.subject = subject;
        this.question = question;
        A = a;
        B = b;
        C = c;
        this.correctAnswer = correctAnswer;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getA() {
        return A;
    }

    public void setA(String a) {
        A = a;
    }

    public String getB() {
        return B;
    }

    public void setB(String b) {
        B = b;
    }

    public String getC() {
        return C;
    }

    public void setC(String c) {
        C = c;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    @Override
    public String toString() {
        return " - " + question + "\n" +
                " A. " + A + "\n" +
                " B. " + B + "\n" +
                " C. " + C + "\n";
    }
}
