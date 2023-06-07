package org.example.commands.generateTimetable;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.example.commands.BotCommand;
import org.example.model.Preference;
import org.example.model.TimeSlot;
import org.example.model.Timetable;

import java.util.*;

public class GenerateTimetable implements BotCommand<SlashCommandInteractionEvent> {

    private final Timetable timetable;

    public GenerateTimetable(Timetable timetable) {
        this.timetable = timetable;
    }

    @Override
    public String getCommand() {
        return "generatetimetable";
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        ArrayList<TimeSlot> allPossibleSlots = generateAllPossibleSlots();
        ArrayList<TimeSlot> schedule = new ArrayList<>();
        ArrayList<Preference> unavailablePref = new ArrayList<>();
        String[] professors = {"tudstk", "George"};
        Map<String, String> professorSubjects = new HashMap<>();
        professorSubjects.put("tudstk", "PA");
        professorSubjects.put("George", "IP");
        String[] subjects = {"PA", "IP"};
        String[] groups = {"B4", "A5", "Course"};
        int professorIndex = 0;
        int subjectIndex = 0;
        Map<String, ArrayList<String>> professorGroups = new HashMap<>();
        Map<String, Boolean> professorCourseGiven = new HashMap<>();
        for (String professor : professors) {
            professorGroups.put(professor, new ArrayList<>());
            professorCourseGiven.put(professor, false);
        }
        for (Preference preference : timetable.getPreferences()) {
            TimeSlot desiredSlot = new TimeSlot(preference.getDay(), preference.getHour(), preference.getSubject(), preference.getGroup(), preference.getUsername());
            if (isTimeSlotAvailable(allPossibleSlots, desiredSlot)) {
                professorGroups.get(preference.getUsername()).add(preference.getGroup());
                schedule.add(desiredSlot);
                removeTimeSlot(allPossibleSlots,desiredSlot);
            } else {
                unavailablePref.add(preference);
            }
        }
        for(Preference preference : unavailablePref){
            for (TimeSlot possibleSlot : allPossibleSlots) {
                if (possibleSlot.getProfessor() == null) {
                    possibleSlot.setProfessor(preference.getUsername());
                    possibleSlot.setSubject(preference.getSubject());
                    possibleSlot.setGroup(preference.getGroup());
                    schedule.add(possibleSlot);
                    removeTimeSlot(allPossibleSlots,possibleSlot);
                    break;
                }
            }
        }
        for (TimeSlot possibleSlot : allPossibleSlots) {
            boolean professorFound = false;
            int startingProfIndex = professorIndex;
            ArrayList<String> professorGroup = null;
            while(!professorFound){
                String professor = professors[professorIndex];
                possibleSlot.setProfessor(professor);
                possibleSlot.setSubject(professorSubjects.get(professor));
                professorGroup = professorGroups.get(professor);
                boolean isCourseGivenToday = isCourseGivenToday(schedule, possibleSlot.getDay(), professor);
                boolean isTimeSlotFree = isTimeSlotFree(schedule, possibleSlot.getDay(), possibleSlot.getHour(), professor);
                if((!isCourseGivenToday && !professorGroup.contains("Course")) && isTimeSlotFree){
                    professorFound = true;
                }
                professorIndex = (professorIndex + 1) % professors.length;
                if(professorIndex == startingProfIndex){
                    break;
                }
            }
            if(!professorFound){
                continue;
            }
            for(String group : groups) {
                if(!professorGroup.contains(group)) {
                    possibleSlot.setGroup(group);
                    professorGroup.add(group);
                    if(group.equals("Course")) {
                        professorCourseGiven.put(professors[professorIndex], true);
                    }
                    break;
                }
            }
            schedule.add(possibleSlot);
        }
        for (TimeSlot timeSlot : schedule) {
            System.out.println(timeSlot.getDay() + " " + timeSlot.getHour() + ":00 - " + timeSlot.getProfessor() + " - Group " + timeSlot.getGroup() + " - Subject: " + timeSlot.getSubject());
        }
        timetable.setSchedule(schedule);
        event.reply("Timetable generated.").setEphemeral(true).queue();
    }

    @Override
    public String getDescription() {
        return "Generates the timetable.";
    }

    public boolean isTimeSlotAvailable(ArrayList<TimeSlot> allPossibleSlots, TimeSlot desiredSlot) {
        for(TimeSlot slot :allPossibleSlots){
            if(slot.getDay().equals(desiredSlot.getDay()) && slot.getHour() == desiredSlot.getHour()){
                return true;
            }
        }
        return false;
    }

    public void removeTimeSlot(ArrayList<TimeSlot> allPossibleSlots, TimeSlot desiredSlot) {
        allPossibleSlots.removeIf(slot -> slot.getDay().equals(desiredSlot.getDay()) && slot.getHour() == desiredSlot.getHour());
    }

    public ArrayList<TimeSlot> generateAllPossibleSlots() {
        ArrayList<TimeSlot> allPossibleSlots = new ArrayList<>();
        ArrayList<String> days = new ArrayList<>(Arrays.asList("Monday", "Tuesday"));
        for (String day : days) {
            for (int hour = 8; hour <= 12; hour += 2) {
                allPossibleSlots.add(new TimeSlot(day, hour));
            }
        }
        return allPossibleSlots;
    }

    public boolean isCourseGivenToday(ArrayList<TimeSlot> schedule, String day, String professor){
        for(TimeSlot timeSlot : schedule){
            if(timeSlot.getDay().equals(day) && timeSlot.getProfessor().equals(professor) && timeSlot.getGroup().equals("Course")){
                return true;
            }
        }
        return false;
    }

    public boolean isTimeSlotFree(ArrayList<TimeSlot> schedule, String day, int hour, String professor){
        for(TimeSlot timeSlot : schedule){
            if(timeSlot.getDay().equals(day) && timeSlot.getHour() == hour && timeSlot.getProfessor().equals(professor)){
                return false;
            }
        }
        return true;
    }
}
