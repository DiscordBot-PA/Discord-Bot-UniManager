package org.example.commands.addPreference;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.example.commands.BotCommand;
import org.example.model.Timetable;

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
        if (event.getOption("day") != null && event.getOption("hour") != null && event.getOption("preference") != null) {

            String day = event.getOption("day").getAsString();
            String hour = event.getOption("hour").getAsString();
            String preference = event.getOption("preference").getAsString();

            timetable.addPreference(day, hour, preference);

            event.reply("Preference added to timetable.").setEphemeral(true).queue();
        } else {
            event.reply("Error: some options are missing.").setEphemeral(true).queue();
        }
    }

    @Override
    public String getDescription() {
        return null;
    }

}
