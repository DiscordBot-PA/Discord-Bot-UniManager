package org.example.model;

import org.example.repository.PreferencesRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Timetable {

    private List<Preference> preferences;
    private PreferencesRepository preferencesRepository; // Inject the repository


    public Timetable(PreferencesRepository preferencesRepository) {
        this.preferencesRepository = preferencesRepository;
        preferences = new ArrayList<>();
    }

    public void addPreference(String day, int hour, String preference, String group, String username) {
        Preference newPreference = new Preference(day, hour, preference, group, username, LocalDateTime.now());
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
        return preferences;
    }

    public void clearPreferences() {
        preferences.clear();
    }
}
