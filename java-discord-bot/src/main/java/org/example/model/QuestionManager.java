package org.example.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuestionManager {

    private List<Question> questions;

    public QuestionManager() {
        Question q1 = new Question("IP","Which design pattern is a creational pattern?", "Abstract Factory", "Adapter", "Command", "A");
        Question q2 = new Question("IP","Which principle is a SOLID principle?", "DRY", "Interface Segregation", "YAGNI", "B");
        Question q3 = new Question("IP","Which principle is a GRASP principle?", "Creator", "Dependency Inversion", "Liskov Substitution", "A");
        Question q4 = new Question("IP","Which of them is a graphical modeling language ?", "AML", "DSL", "UML", "C");
        Question q5 = new Question("PA","Each executing thread is an instance of the following class?", "Executor", "Thread", "JVMThread", "B");
        Question q6 = new Question("PA","Other than JPQL, JPA specifications offer the following API for implementing type-safe dynamic queries?", "Dynamic API", "Reflection API", "Criteria API", "C");
        Question q7 = new Question("PA","Which layout manager class could be used to arrange components in 5 regions north, south, east, west, center?", "BorderLayout", "NullLayout", "GridLayout", "A");
        Question q8 = new Question("PA","Working with objects of type java.io.File could cause exceptions of type?", "SocketException", "IOException", "SQLException", "B");
        this.questions = new ArrayList<>(Arrays.asList(q1,q2,q3,q4,q5,q6,q7,q8));
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
