package org.example.commands.hello;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.example.commands.BotCommand;

public class Hello implements BotCommand <SlashCommandInteractionEvent>{

    @Override
    public String getCommand() {
        return "hello";
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.reply("Hello!").setEphemeral(true).queue();
    }

    @Override
    public String getDescription() {
        return "This command returns \"Hello!\".";
    }
}