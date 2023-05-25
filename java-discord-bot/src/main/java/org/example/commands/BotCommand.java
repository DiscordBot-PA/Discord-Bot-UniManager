package org.example.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public interface BotCommand {
    String getCommand();
    void execute(SlashCommandInteractionEvent event);
    String getDescription();
}
