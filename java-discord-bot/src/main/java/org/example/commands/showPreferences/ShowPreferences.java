package org.example.commands.showPreferences;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.example.commands.BotCommand;
import org.example.model.Timetable;

import java.util.Map;

public class ShowPreferences implements BotCommand<SlashCommandInteractionEvent> {

    private final Timetable timetable;

    public ShowPreferences(Timetable timetable) {
        this.timetable = timetable;
    }

    @Override
    public String getCommand() {
        return "showpreferences";
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        StringBuilder timetableMessage = new StringBuilder("Timetable preferences:\n");
        for (String day : timetable.getSchedule().keySet()) {
            timetableMessage.append(day).append(":\n");
            for (Map.Entry<String, String> entry : timetable.getSchedule().get(day).entrySet()) {
                String hour = entry.getKey();
                String preference = entry.getValue();
                timetableMessage.append("- ").append(hour).append(": ").append(preference).append("\n");
            }
            timetableMessage.append("\n");
        }
        event.reply(timetableMessage.toString()).setEphemeral(true).queue();
    }

    @Override
    public String getDescription() {
        return null;
    }

}