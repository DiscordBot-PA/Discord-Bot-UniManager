package org.example.commands.generateTimetable;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.example.commands.BotCommand;
import org.example.model.Preference;
import org.example.model.TimeSlot;
import org.example.model.Timetable;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;

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
        String[] subjects = {"IP", "PA"};
        String[] groups = {"B4", "A5"};
        int professorIndex = 0;
        int subjectIndex = 0;
        int groupIndex = 0;

        // Procesăm mai întâi preferințele
        for (Preference preference : timetable.getPreferences()) {
            TimeSlot desiredSlot = new TimeSlot(preference.getDay(), preference.getHour(), preference.getSubject(), preference.getGroup(), preference.getUsername());
            if (isTimeSlotAvailable(allPossibleSlots, desiredSlot)) {
                schedule.add(desiredSlot);
                removeTimeSlot(allPossibleSlots,desiredSlot);
            } else {
                System.out.println(preference);
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
        // După procesarea preferințelor, alocăm sloturile rămase
        for (TimeSlot possibleSlot : allPossibleSlots) {
            // Atribuim slotul profesorului, materiei și grupului
            possibleSlot.setProfessor(professors[professorIndex]);
            possibleSlot.setSubject(subjects[subjectIndex]);
            possibleSlot.setGroup(groups[groupIndex]);
            schedule.add(possibleSlot);

            // Avansăm la următorul profesor, materie și grup
            professorIndex = (professorIndex + 1) % professors.length;
            subjectIndex = (subjectIndex + 1) % subjects.length;
            groupIndex = (groupIndex + 1) % groups.length;
        }

        // Print the schedule
        for (TimeSlot timeSlot : schedule) {
            System.out.println(timeSlot.getDay() + " " + timeSlot.getHour() + ":00 - " + timeSlot.getProfessor() + " - Group " + timeSlot.getGroup() + " - Subject: " + timeSlot.getSubject());
        }

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
}
