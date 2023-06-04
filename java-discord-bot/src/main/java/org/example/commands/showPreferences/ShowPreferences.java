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
            int hour = preference.getHour();
            String subject = preference.getSubject();
            String group = preference.getGroup();
            String username = preference.getUsername();

            if (daySubjects.containsKey(day)) {
                daySubjects.get(day).append("\n- ").append(hour).append(": ").append(subject).append(", ").append(group).append(" -> ").append(username);
            } else {
                StringBuilder line = new StringBuilder(day + ":\n- " + hour + ": " + subject + ", "+ group + " -> " + username);
                daySubjects.put(day, line);
            }
        }
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
