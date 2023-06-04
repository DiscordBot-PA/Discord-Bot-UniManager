package org.example;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.example.commands.CommandManager;
import org.example.model.Student;
import org.example.repository.StudentRepository;

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

        OptionData dayOption = new OptionData(OptionType.STRING, "day", "ziua");
        OptionData hourOption = new OptionData(OptionType.STRING, "hour", "ora");
        OptionData preferenceOption = new OptionData(OptionType.STRING, "subject", "materia");
        OptionData groupOption = new OptionData(OptionType.STRING, "group", "grupa");

        OptionData registrationNrOption = new OptionData(OptionType.STRING, "registrationnumber", "student registration number");
        OptionData gradeValueOption = new OptionData(OptionType.STRING, "value", "grade value");
        OptionData subjectOption = new OptionData(OptionType.STRING, "subject", "subject title");

        jda.upsertCommand("hello","This command returns \"Hello!\"").setGuildOnly(true).queue();
        jda.upsertCommand("help","Shows all commands and their description.").setGuildOnly(true).queue();
        jda.upsertCommand("addpreference","This command adds a preference.").addOptions(dayOption, hourOption , preferenceOption, groupOption).setGuildOnly(true).queue();
        jda.upsertCommand("showpreferences","Shows all preferences in the timetable.").setGuildOnly(true).queue();
        jda.upsertCommand("addgrade","Add a grade to a student.").addOptions(registrationNrOption, gradeValueOption, subjectOption).setGuildOnly(true).queue();
        jda.upsertCommand("showgrades","Add a grade to a student.").addOptions(registrationNrOption).setGuildOnly(true).queue();


    }

    public static void main(String[] args) throws LoginException {
        DiscordBot discordBot = new DiscordBot();

        StudentRepository studentRepository = new StudentRepository();
        Student student = new Student();
        student.setName("Dorel");
        studentRepository.create(student);

    }
}