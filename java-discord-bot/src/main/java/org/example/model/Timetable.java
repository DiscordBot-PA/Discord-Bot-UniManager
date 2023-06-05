package org.example.model;

import org.example.repository.PreferencesRepository;
import org.example.repository.ScheduleRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Timetable {

    private List<Preference> preferences;
    private PreferencesRepository preferencesRepository; // Inject the repository
    ArrayList<TimeSlot> schedule;

    public ArrayList<TimeSlot> getSchedule() {
        return schedule;
    }

    public void setSchedule(ArrayList<TimeSlot> schedule) {
        this.schedule = schedule;
    }

    public Timetable(PreferencesRepository preferencesRepository) {
        this.preferencesRepository = preferencesRepository;
        preferences = new ArrayList<>();
    }

    public void addPreference(String day, int hour, String preference, String group, String subjectComponent, String username) {
        Preference newPreference = new Preference(day, hour, preference, group, subjectComponent, username, LocalDateTime.now());
        preferences.add(newPreference);
        preferencesRepository.save(newPreference);

    }

    public String isHourAvailable(String day, int hour) {
        for (Preference preference : preferences) {
            if (preference.getDay().equals(day) && preference.getHour() == hour) {
                return preference.getSubject();
            }
        }
        return null;
    }

    public List<Preference> getPreferences() {
        return preferencesRepository.getAllPreferences();
    }

    public List<TimeSlot> getTimeSlotsBySubjectAndGroup(String subject, String group) {
        List<TimeSlot> subjectTimeSlots = new ArrayList<>();
        for (TimeSlot timeSlot : schedule) {
            if (timeSlot.getSubject().equalsIgnoreCase(subject) && timeSlot.getGroup().equalsIgnoreCase(group)) {
                subjectTimeSlots.add(timeSlot);
            }
        }
        return subjectTimeSlots;
    }

    public List<TimeSlot> getTimeSlotsBySubject(String subject) {
        List<TimeSlot> subjectTimeSlots = new ArrayList<>();
        for (TimeSlot timeSlot : schedule) {
            if (timeSlot.getSubject().equalsIgnoreCase(subject)) {
                subjectTimeSlots.add(timeSlot);
            }
        }
        return subjectTimeSlots;
    }

    public List<TimeSlot> getTimeSlotsByGroup(String group) {
        List<TimeSlot> subjectTimeSlots = new ArrayList<>();
        for (TimeSlot timeSlot : schedule) {
            if (timeSlot.getGroup().equalsIgnoreCase(group)) {
                subjectTimeSlots.add(timeSlot);
            }
        }
        return subjectTimeSlots;
    }

    public void clearPreferences() {
        preferences.clear();
    }
}
