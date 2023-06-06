package org.example.commands.quiz;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.example.commands.BotCommand;

import java.util.HashMap;
import java.util.Map;

public class StartQuiz implements BotCommand<SlashCommandInteractionEvent> {

    @Override
    public String getCommand() {
        return "start";
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
    }

    @Override
    public String getDescription() {
        return "This command returns \"Hello!\".";
    }
}