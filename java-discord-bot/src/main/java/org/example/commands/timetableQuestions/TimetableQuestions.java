package org.example.commands.timetableQuestions;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.example.commands.BotCommand;
import org.example.model.TimeSlot;
import org.example.model.Timetable;

import java.util.*;

public class TimetableQuestions implements BotCommand<SlashCommandInteractionEvent> {

    private final Timetable timetable;

    public TimetableQuestions(Timetable timetable) {
        this.timetable = timetable;
    }

    @Override
    public String getCommand() {
        return "asktimetable";
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        List<TimeSlot> timeSlotList;
        StringBuilder timetableMessage = new StringBuilder();
        Map<String, StringBuilder> daySubjects = new HashMap<>();
        List<String> availableSubjects = new ArrayList<>(Arrays.asList("IP", "PA"));
        List<String> availableGroups = new ArrayList<>(Arrays.asList("B4", "A5"));

        if (event.getOption("group") != null && event.getOption("subject") != null) {

            String subject = event.getOption("subject").getAsString();
            String group = event.getOption("group").getAsString();
            if(!availableGroups.contains(group))
            {
                event.reply("Error: available groups are A5 and B4.").setEphemeral(true).queue();
            }
            else if(!availableSubjects.contains(subject)){
                event.reply("Error: available subjects are IP and PA.").setEphemeral(true).queue();
            }
            else {


                timeSlotList = timetable.getTimeSlotsBySubjectAndGroup(subject, group);
                timetableMessage.append(subject).append(" - ").append(group).append(":\n");

                for (TimeSlot timeSlot : timeSlotList) {
                    String day = timeSlot.getDay();
                    int hour = timeSlot.getHour();
                    String line = "- " + hour + ":00";

                    if (daySubjects.containsKey(day)) {
                        daySubjects.get(day).append("\n").append(line);
                    } else {
                        StringBuilder subjectLine = new StringBuilder(day).append(":\n").append(line);
                        daySubjects.put(day, subjectLine);
                    }
                }

                for (StringBuilder line : daySubjects.values()) {
                    timetableMessage.append(line).append("\n");
                }

                event.reply(timetableMessage.toString()).setEphemeral(true).queue();
            }

        } else if (event.getOption("group") == null && event.getOption("subject") != null) {

            String subject = event.getOption("subject").getAsString();
            if(!availableGroups.contains(subject))
            {
                event.reply("Error: available subjects are IP and PA.").setEphemeral(true).queue();
            }
            else {
                timeSlotList = timetable.getTimeSlotsBySubject(subject);
                timetableMessage.append(subject).append(":\n");

                for (TimeSlot timeSlot : timeSlotList) {
                    String day = timeSlot.getDay();
                    int hour = timeSlot.getHour();
                    String line = "- " + hour + ":00 - " + timeSlot.getGroup();

                    if (daySubjects.containsKey(day)) {
                        daySubjects.get(day).append("\n").append(line);
                    } else {
                        StringBuilder subjectLine = new StringBuilder(day).append(":\n").append(line);
                        daySubjects.put(day, subjectLine);
                    }
                }

                for (StringBuilder line : daySubjects.values()) {
                    timetableMessage.append(line).append("\n");
                }

                event.reply(timetableMessage.toString()).setEphemeral(true).queue();
            }


        } else if (event.getOption("group") != null && event.getOption("subject") == null) {

            String group = event.getOption("group").getAsString();
            if(!availableGroups.contains(group))
            {
                event.reply("Error: available groups are A5 and B4.").setEphemeral(true).queue();
            }
            else {
                timeSlotList = timetable.getTimeSlotsByGroup(group);
                timetableMessage.append(group).append(":\n");

                for (TimeSlot timeSlot : timeSlotList) {
                    String day = timeSlot.getDay();
                    int hour = timeSlot.getHour();
                    String line = "- " + hour + ":00 - " + timeSlot.getSubject();

                    if (daySubjects.containsKey(day)) {
                        daySubjects.get(day).append("\n").append(line);
                    } else {
                        StringBuilder subjectLine = new StringBuilder(day).append(":\n").append(line);
                        daySubjects.put(day, subjectLine);
                    }
                }

                for (StringBuilder line : daySubjects.values()) {
                    timetableMessage.append(line).append("\n");
                }

                event.reply(timetableMessage.toString()).setEphemeral(true).queue();
            }

        } else {
            event.reply("Error: option combination is not available.").setEphemeral(true).queue();
        }
    }


    @Override
    public String getDescription() {
        return null;
    }
}
