package org.example.model;

import org.example.repository.PreferencesRepository;

import java.util.ArrayList;
import java.util.List;

public class Timetable {

    private List<Preference> preferences;
    private PreferencesRepository preferencesRepository; // Inject the repository

    public Timetable(PreferencesRepository preferencesRepository) {
        this.preferencesRepository = preferencesRepository;
        preferences = new ArrayList<>();
    }

    public void addPreference(String day, String hour, String preference) {
        Preference newPreference = new Preference(day, hour, preference);
        preferences.add(newPreference);
        preferencesRepository.save(newPreference);

    }

    public String isHourAvailable(String day, String hour) {
        for (Preference preference : preferences) {
            if (preference.getDay().equals(day) && preference.getHour().equals(hour)) {
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
