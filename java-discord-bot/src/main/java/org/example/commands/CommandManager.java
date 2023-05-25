package org.example.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.example.commands.hello.Hello;
import org.example.commands.help.Help;
import org.example.commands.rss.JavaRSS;

import java.util.ArrayList;
import java.util.List;

public class CommandManager extends ListenerAdapter {
    private final List<BotCommand> commands = new ArrayList<>();

    public CommandManager() {
        commands.add(new Hello());
        commands.add(new Help(this));
        commands.add(new JavaRSS());
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        String message = event.getName();

        for (BotCommand command : commands) {
            if (message.startsWith(command.getCommand()) && !message.startsWith("!")) {
                command.execute(event);
                break;
            }
        }
    }
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();

        for (BotCommand command : commands) {
            if (message.startsWith(command.getCommand())) {
                command.execute(event);
                break;
            }
        }
    }

    public List<BotCommand> getCommands() {
        return commands;
    }
}