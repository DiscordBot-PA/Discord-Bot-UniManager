package org.example;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.example.events.InteractionEventListener;
import org.example.events.MessageEventListener;
import org.example.events.ReadyEventListener;
import org.example.model.Student;

import javax.security.auth.login.LoginException;

public class DiscordBot {
    private final Dotenv config;
    public DiscordBot() throws LoginException{
        Student student = new Student();
        student.setName("denis");
        StudentRepository studentRepository = new StudentRepository();
        studentRepository.create(student);
        config = Dotenv.configure().load();
        String token = config.get("TOKEN");
        JDABuilder jdaBuilder = JDABuilder.createDefault(token);
        JDA jda = jdaBuilder
                .enableIntents(GatewayIntent.MESSAGE_CONTENT,GatewayIntent.GUILD_MESSAGES)
                .addEventListeners(new ReadyEventListener(), new MessageEventListener(),new InteractionEventListener())
                .build();

        Member member;
        jda.upsertCommand("smecheria","This is a smecherie command").setGuildOnly(true).queue();
        jda.upsertCommand("foamea","Lists all fomisti").setGuildOnly(true).queue();
        jda.upsertCommand("prostia","Give's you a random prost!").setGuildOnly(true).queue();
        jda.upsertCommand("nume","iti da numele de discord").setGuildOnly(true).queue();
        jda.upsertCommand("id","id discord").setGuildOnly(true).queue();
        jda.upsertCommand("role","discord role").setGuildOnly(true).queue();
    }
    public static void main(String[] args) throws LoginException {
        DiscordBot discordBot = new DiscordBot();

    }
}
