package org.example.commands.quiz;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.example.commands.BotCommand;
import org.example.model.Question;

import java.util.*;

public class StartQuiz implements BotCommand<SlashCommandInteractionEvent> {

    private final List<Question> questions;

    public List<Question> getQuestions() {
        return questions;
    }

    public StartQuiz(List<Question> questions) {
        this.questions = questions;
    }

    @Override
    public String getCommand() {
        return "startquiz";
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        String subject = event.getOption("subject").getAsString();
        StringBuilder sb = new StringBuilder();

        if(subject.equalsIgnoreCase("IP")){

            for(Question q : questions){
                if(q.getSubject().equalsIgnoreCase("IP")) {
                    sb.append(q).append("\n");
                }
            }
            event.reply(String.valueOf(sb)).setEphemeral(true).queue();
        }
        else if(subject.equalsIgnoreCase("PA")){
            for(Question q : questions){
                if(q.getSubject().equalsIgnoreCase("PA")) {
                    sb.append(q).append("\n");
                }
            }
            event.reply(String.valueOf(sb)).setEphemeral(true).queue();
        }
        else{
            event.reply("The available subjects are IP and PA.").setEphemeral(true).queue();
        }
    }

    @Override
    public String getDescription() {
        return "This command returns \"Hello!\".";
    }
}