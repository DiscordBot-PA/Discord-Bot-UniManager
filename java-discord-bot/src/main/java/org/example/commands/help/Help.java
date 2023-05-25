package org.example.commands.help;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.example.commands.BotCommand;
import org.example.commands.CommandManager;

public class Help implements BotCommand {
    private final CommandManager commandManager;

    public Help(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    @Override
    public String getCommand() {
        return "help";
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        StringBuilder helpMessage = new StringBuilder("Available commands:\n");

        helpMessage.append("```");
        for (BotCommand command : commandManager.getCommands()) {
            helpMessage.append(command.getCommand()).append(" => ").append(command.getDescription()).append("\n");
        }
        helpMessage.append("```");

        event.reply(helpMessage.toString()).setEphemeral(true).queue();
    }

    @Override
    public String getDescription() {
        return "This command returns all the available commands.";
    }
}
