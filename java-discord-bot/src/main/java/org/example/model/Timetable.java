package org.example.model;

import java.util.HashMap;
import java.util.Map;

public class Timetable {
    private Map<String, Map<String, String>> schedule;

    // Mapez zilele saptamanii la un map care mapeaza materiile la orele preferate
    // Luni -> {PA -> 12:00 - 14:00, IP -> 18:00 - 20:00, ...}
    // Marti -> {...}

    public Timetable() {
        schedule = new HashMap<>();
    }

    public void addPreference(String day, String hour, String preference) {
        schedule.putIfAbsent(day, new HashMap<>());
        schedule.get(day).put(hour, preference);
    }

    public String isHourAvailable(String day, String hour) {
        if (schedule.containsKey(day) && schedule.get(day).containsKey(hour)) {
            return schedule.get(day).get(hour);
        } else {
            return null;
        }
    }

    public Map<String, Map<String, String>> getSchedule() {
        return schedule;
    }

    public void clearSchedule() {
        schedule.clear();
    }

}
