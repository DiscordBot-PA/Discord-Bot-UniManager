package org.example.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public interface BotCommand <T>{
    String getCommand();
    void execute(T event);
    String getDescription();
}
