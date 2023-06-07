package org.example.commands.timetableQuestions;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
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
        List<String> availableGroups = new ArrayList<>(Arrays.asList("B4", "A5","Course"));
        List<Integer> availableHours = new ArrayList<>(Arrays.asList(8, 10, 12));
        List<String> availableDays = new ArrayList<>(Arrays.asList("Monday", "Tuesday"));
        List<String> availableTeachers = new ArrayList<>(Arrays.asList("tudstk", "Tuesday"));

        if (event.getOption("group") != null && event.getOption("subject") != null && event.getOption("hour") == null && event.getOption("day") == null && event.getOption("teacher") == null) {

            String subject = event.getOption("subject").getAsString();
            String group = event.getOption("group").getAsString();
            if(!availableGroups.contains(group))
            {
                event.reply("Error: available groups are A5, B4 and Course.").setEphemeral(true).queue();
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

        } else if (event.getOption("group") == null && event.getOption("subject") != null && event.getOption("hour") == null && event.getOption("day") == null && event.getOption("teacher") == null) {

            String subject = event.getOption("subject").getAsString();
            if(!availableSubjects.contains(subject))
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


        } else if (event.getOption("group") != null && event.getOption("subject") == null && event.getOption("hour") == null && event.getOption("day") == null && event.getOption("teacher") == null) {

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

        }
        else if (event.getOption("hour") != null && event.getOption("day") != null && event.getOption("subject") == null && event.getOption("group") == null && event.getOption("teacher") == null) {

            // HOUR && DAY

            int hour = event.getOption("hour").getAsInt();
            String day = event.getOption("day").getAsString();

            if(!availableHours.contains(hour))
            {
                event.reply("Error: available hours are 8, 10, 12.").setEphemeral(true).queue();
            }
            else if(!availableDays.contains(day))
            {
                event.reply("Error: available days are Monday and Tuesday.").setEphemeral(true).queue();
            }
            else {
                timeSlotList = timetable.getTimeSlotsByHour(hour, day);
                timetableMessage.append(day).append(" - ").append(hour).append(":00").append(":\n");

                for (TimeSlot timeSlot : timeSlotList) {
                    String subject= timeSlot.getSubject();
                    String group = timeSlot.getGroup();
                    String line = "- " + subject + " - " + group;

                    if (daySubjects.containsKey(day)) {
                        daySubjects.get(day).append(line).append("\n");
                    } else {
                        StringBuilder subjectLine = new StringBuilder().append(line).append("\n");
                        daySubjects.put(day, subjectLine);
                    }
                }

                for (StringBuilder line : daySubjects.values()) {
                    timetableMessage.append(line).append("\n");
                }

                event.reply(timetableMessage.toString()).setEphemeral(true).queue();
            }

        }
        else if(event.getOption("teacher") != null && event.getOption("subject") == null && event.getOption("day") != null && event.getOption("group") == null && event.getOption("hour") == null){
            String day = event.getOption("day").getAsString();
            String teacher = event.getOption("teacher").getAsString();

            if(!availableTeachers.contains(teacher))
            {
                event.reply("Error: Available teachers are George and tudstk.").setEphemeral(true).queue();
            }
            else if(!availableDays.contains(day))
            {
                event.reply("Error: available days are Monday and Tuesday.").setEphemeral(true).queue();
            }
            else {
                timeSlotList = timetable.getTimeSlotsByDayAndTeacher(day, teacher);
                timetableMessage.append(day).append(" - ").append(teacher).append(":\n");
                System.out.println("T I M E S L O T S   S I Z E : " + timeSlotList.size());
                for (TimeSlot timeSlot : timeSlotList) {
                    String subject= timeSlot.getSubject();
                    int hour = timeSlot.getHour();
                    String line = hour + ":00 - " + subject;

                    if (daySubjects.containsKey(day)) {
                        daySubjects.get(day).append(line).append("\n");
                    } else {
                        StringBuilder subjectLine = new StringBuilder().append(line).append("\n");
                        daySubjects.put(day, subjectLine);
                    }
                }

                for (StringBuilder line : daySubjects.values()) {
                    timetableMessage.append(line).append("\n");
                }

                event.reply(timetableMessage.toString()).setEphemeral(true).queue();
            }
        }
        else {
            event.reply("Error: option combination is not available.").setEphemeral(true).queue();
        }
    }

    @Override
    public String getDescription() {
        return null;
    }
}
