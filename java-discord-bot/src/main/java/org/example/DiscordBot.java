package org.example;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.example.commands.CommandManager;

import javax.security.auth.login.LoginException;

public class DiscordBot {
    //private final Dotenv config;
    public DiscordBot() throws LoginException{
//        config = Dotenv.configure().load();
//        String token = config.get("TOKEN");
        JDABuilder jdaBuilder = JDABuilder.createDefault("MTEwOTE1MzYxNjc5MjY1Mzk1OA.G0nuza.13YRjeCJqvnjHoWxnYfsmzTN_lWcszPMuuiKTs");
        JDA jda = jdaBuilder
                .enableIntents(GatewayIntent.MESSAGE_CONTENT,GatewayIntent.GUILD_MESSAGES)
                .addEventListeners(new CommandManager())
                .build();

        jda.upsertCommand("hello","This command returns \"Hello!\"").setGuildOnly(true).queue();
        jda.upsertCommand("help","Shows all commands and their description.").setGuildOnly(true).queue();

    }

    public static void main(String[] args) throws LoginException {
        DiscordBot discordBot = new DiscordBot();
    }
}