package org.example.model;

public class TimeSlot {
    private String day;
    private int hour;
    private String subject;
    private String group;
    private String professor;

    public TimeSlot(String day, int hour) {
        this.day = day;
        this.hour = hour;
    }

    public TimeSlot(String day, int hour, String subject, String group, String professor) {
        this.day = day;
        this.hour = hour;
        this.subject = subject;
        this.group = group;
        this.professor = professor;
    }

    public String getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    @Override
    public String toString() {
        return "TimeSlot{" +
                "day='" + day + '\'' +
                ", hour=" + hour +
                ", subject='" + subject + '\'' +
                ", group='" + group + '\'' +
                ", professor='" + professor + '\'' +
                '}';
    }
}

