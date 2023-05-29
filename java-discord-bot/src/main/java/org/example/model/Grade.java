package org.example.model;

import javax.persistence.*;

@Entity
@Table(name = "grades")

public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int value;

    @ManyToOne
    @JoinColumn(name = "registration_number", referencedColumnName = "registration_number")
    private Student student;

    @Column(name="subject_title")
    private String subjectTitle;

    public Grade() {
    }

    public Grade( int value, Student student, String subjectTitle) {
        this.value = value;
        this.student = student;
        this.subjectTitle = subjectTitle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Student getStudentId() {
        return student;
    }

    public void setStudentId(Student student) {
        this.student = student;
    }

    public String getSubjectTitle() {
        return subjectTitle;
    }

    public void setSubjectTitle(String subjectTitle) {
        this.subjectTitle = subjectTitle;
    }
}
