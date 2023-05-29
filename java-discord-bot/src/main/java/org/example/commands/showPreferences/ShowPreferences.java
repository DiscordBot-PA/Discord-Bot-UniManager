package org.example.commands.showPreferences;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.example.commands.BotCommand;
import org.example.model.Preference;
import org.example.model.Timetable;
import org.example.repository.PreferencesRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

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
        for (Preference preference : preferences) {
            timetableMessage.append(preference.getDay()).append(":\n");
            timetableMessage.append("- ").append(preference.getHour()).append(": ").append(preference.getSubject()).append("\n");
        }
        event.reply(timetableMessage.toString()).setEphemeral(true).queue();
    }

    @Override
    public String getDescription() {
        return null;
    }
}
