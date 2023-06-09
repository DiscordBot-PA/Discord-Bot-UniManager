package org.example;

import io.github.cdimascio.dotenv.Dotenv;
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
    private final Dotenv config;
    public DiscordBot() throws LoginException{
        config = Dotenv.configure().load();
        String token = config.get("TOKEN");
        JDABuilder jdaBuilder = JDABuilder.createDefault(token);
        JDA jda = jdaBuilder
                .enableIntents(GatewayIntent.MESSAGE_CONTENT,GatewayIntent.GUILD_MESSAGES)
                .addEventListeners(new CommandManager())
                .build();

        OptionData dayOption = new OptionData(OptionType.STRING, "day", "Days: Monday, Tuesday.");
        OptionData hourOption = new OptionData(OptionType.STRING, "hour", "Hours: 8, 10, 12");
        OptionData preferenceOption = new OptionData(OptionType.STRING, "subject", "Subjects: IP, PA.");
        OptionData groupOption = new OptionData(OptionType.STRING, "group", "Groups: B4, A5.");

        OptionData registrationNrOption = new OptionData(OptionType.STRING, "registrationnumber", "student registration number");
        OptionData gradeValueOption = new OptionData(OptionType.STRING, "value", "grade value");
        OptionData subjectOption = new OptionData(OptionType.STRING, "subject", "subject title");
        OptionData teacherOption = new OptionData(OptionType.STRING, "teacher", "teacher's name");

        OptionData optionA = new OptionData(OptionType.STRING, "q1", "Question 1");
        OptionData optionB = new OptionData(OptionType.STRING, "q2", "Question 2");
        OptionData optionC = new OptionData(OptionType.STRING, "q3", "Question 3");
        OptionData optionD = new OptionData(OptionType.STRING, "q4", "Question 4");


        jda.upsertCommand("hello","This command returns \"Hello!\"").setGuildOnly(true).queue();
        jda.upsertCommand("help","Shows all commands and their description.").setGuildOnly(true).queue();
        jda.upsertCommand("addpreference","This command adds a preference.").addOptions(dayOption, hourOption , preferenceOption, groupOption).setGuildOnly(true).queue();
        jda.upsertCommand("showpreferences","Shows all preferences in the timetable.").setGuildOnly(true).queue();
        jda.upsertCommand("addgrade","Add a grade to a student.").addOptions(registrationNrOption, gradeValueOption, subjectOption).setGuildOnly(true).queue();
        jda.upsertCommand("showgrades","Add a grade to a student.").addOptions(registrationNrOption).setGuildOnly(true).queue();
        jda.upsertCommand("generatetimetable","Generates the timetable.").setGuildOnly(true).queue();
        jda.upsertCommand("asktimetable","Ask timetable whatever you want based on subject, group and hour.").addOptions(groupOption, subjectOption, hourOption, dayOption, teacherOption).setGuildOnly(true).queue();
        jda.upsertCommand("startquiz","Start a quiz.").addOptions(subjectOption).setGuildOnly(true).queue();
        jda.upsertCommand("submitquiz","Submit the answers for a quiz.").addOptions(subjectOption,optionA, optionB, optionC, optionD).setGuildOnly(true).queue();
        jda.upsertCommand("showtimetable","Shows the timetable.").setGuildOnly(true).queue();

    }

    public static void main(String[] args) throws LoginException {
        DiscordBot discordBot = new DiscordBot();
    }
}