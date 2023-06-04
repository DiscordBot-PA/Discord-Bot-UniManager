package org.example.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "preferences")
public class Preference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "day")
    private String day;

    @Column(name = "hour")
    private int hour;

    @Column(name = "subject")
    private String subject;

    @Column(name = "subject_component")
    private String subjectComponent;

    @Column(name = "group_name")
    private String group;

    @Column(name = "username")
    private String username;

    @Column(name = "recorded_at")
    private LocalDateTime date;

    public Preference() {
    }


    public String getSubjectComponent() {
        return subjectComponent;
    }

    public void setSubjectComponent(String subjectComponent) {
        this.subjectComponent = subjectComponent;
    }

    public Preference(String day, int hour, String subject, String group, String subjectComponent , String username, LocalDateTime date) {
        this.day = day;
        this.hour = hour;
        this.subject = subject;
        this.group = group;
        this.subjectComponent = subjectComponent;
        this.username = username;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public String getGroup() {
        return group;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public String getUsername() {
        return username;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Preference{" +
                "id=" + id +
                ", day='" + day + '\'' +
                ", hour=" + hour +
                ", subject='" + subject + '\'' +
                ", group='" + group + '\'' +
                ", username='" + username + '\'' +
                ", date=" + date +
                '}';
    }
}
