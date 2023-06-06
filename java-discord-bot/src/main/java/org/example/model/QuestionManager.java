package org.example.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuestionManager {

    private List<Question> questions;

    public QuestionManager() {
        Question q1 = new Question("IP","Ce este ingineria programarii?", "ceva usor", "ceva greu", "ceva mediu", "B");
        Question q2 = new Question("IP","Ce este Java?", "ceva usor", "ceva greu", "ceva mediu", "A");
        Question q3 = new Question("IP","Ce este viata?", "ceva usor", "ceva greu", "ceva mediu", "C");
        Question q4 = new Question("IP","Ce este TW?", "ceva usor", "ceva greu", "ceva mediu", "C");
        Question q5 = new Question("PA","Mai poti?", "da", "nu", "poate", "B");
        Question q6 = new Question("PA","question?", "ceva usor", "ceva greu", "ceva mediu", "A");
        Question q7 = new Question("PA","question?", "ceva usor", "ceva greu", "ceva mediu", "C");
        Question q8 = new Question("PA","question?", "ceva usor", "ceva greu", "ceva mediu", "C");
        this.questions = new ArrayList<>(Arrays.asList(q1,q2,q3,q4,q5,q6,q7,q8));
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
