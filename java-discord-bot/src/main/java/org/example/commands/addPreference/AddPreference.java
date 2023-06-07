package org.example.commands.addPreference;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.example.commands.BotCommand;
import org.example.model.Timetable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddPreference implements BotCommand<SlashCommandInteractionEvent> {

    private final Timetable timetable;

    public AddPreference(Timetable timetable) {
        this.timetable = timetable;
    }

    @Override
    public String getCommand() {
        return "addpreference";
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        List<String> availableDays = new ArrayList<>(Arrays.asList("Monday", "Tuesday"));
        List<String> availableSubjects = new ArrayList<>(Arrays.asList("IP", "PA"));
        List<String> availableGroups = new ArrayList<>(Arrays.asList("B4", "A5","Course"));
        List<Integer> availableHours = new ArrayList<>(Arrays.asList(8, 10, 12));

        if (event.getOption("day") != null && event.getOption("hour") != null && event.getOption("subject") != null && event.getOption("group") != null) {

            String day = event.getOption("day").getAsString();
            int hour = event.getOption("hour").getAsInt();
            String subject = event.getOption("subject").getAsString();
            String group = event.getOption("group").getAsString();

            String username = event.getUser().getName();

            if(!availableDays.contains(day)){
                event.reply("Error: the available days are: Monday, Tuesday.").setEphemeral(true).queue();
            }
            else if(!availableSubjects.contains(subject)){
                event.reply("Error: the available subjects are: IP, PA.").setEphemeral(true).queue();
            }
            else if(!availableHours.contains(hour)){
                event.reply("Error: the available hours are: 8, 10, 12.").setEphemeral(true).queue();
            }
            else if(!availableGroups.contains(group)){
                event.reply("Error: the available groups are: B4, A5, Course.").setEphemeral(true).queue();
            }

            else{
                timetable.addPreference(day, hour, subject, group, username);
                event.reply("Preference added to timetable.").setEphemeral(true).queue();
            }
        } else {
            event.reply("Error: some options are missing.").setEphemeral(true).queue();
        }
    }

    @Override
    public String getDescription() {
        return null;
    }

}
