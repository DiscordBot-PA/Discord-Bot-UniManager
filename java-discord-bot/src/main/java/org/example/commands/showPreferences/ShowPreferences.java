package org.example.commands.showPreferences;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.example.commands.BotCommand;
import org.example.model.Preference;
import org.example.repository.PreferencesRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowPreferences implements BotCommand<SlashCommandInteractionEvent> {

    private final PreferencesRepository preferencesRepository;

    public ShowPreferences() {
        preferencesRepository = new PreferencesRepository();
    }

    @Override
    public String getCommand() {
        return "showpreferences";
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        List<Preference> preferences = preferencesRepository.getAllPreferences();
        StringBuilder timetableMessage = new StringBuilder("Timetable preferences:\n");
        Map<String, StringBuilder> daySubjects = new HashMap<>();

        for (Preference preference : preferences) {
            String day = preference.getDay();
            String hour = preference.getHour();
            String subject = preference.getSubject();

            if (daySubjects.containsKey(day)) {
                // Append the subject to the existing line
                daySubjects.get(day).append("\n- ").append(hour).append(": ").append(subject);
            } else {
                // Create a new line for the day and subject
                StringBuilder line = new StringBuilder(day + ":\n- " + hour + ": " + subject);
                daySubjects.put(day, line);
            }
        }

// Append the day and subject lines to the timetableMessage string
        for (StringBuilder line : daySubjects.values()) {
            timetableMessage.append(line).append("\n");
        }


        event.reply(timetableMessage.toString()).setEphemeral(true).queue();
    }

    @Override
    public String getDescription() {
        return null;
    }
}
