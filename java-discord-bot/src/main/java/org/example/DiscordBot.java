package org.example;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.example.commands.CommandManager;

import javax.security.auth.login.LoginException;

public class DiscordBot {

    public static void main(String[] args) throws LoginException {
        String token = "your-token";
        JDABuilder jdaBuilder = JDABuilder.createDefault(token);
        JDA jda = jdaBuilder
                .enableIntents(GatewayIntent.MESSAGE_CONTENT,GatewayIntent.GUILD_MESSAGES)
                .addEventListeners(new CommandManager())
                .build();

        jda.upsertCommand("hello","This command returns \"Hello!\"").setGuildOnly(true).queue();
        jda.upsertCommand("help","Shows all commands and their description.").setGuildOnly(true).queue();

    }
}
