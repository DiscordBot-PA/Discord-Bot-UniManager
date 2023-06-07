package org.example.commands.quiz;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.example.commands.BotCommand;
import org.example.model.Question;
import org.example.model.QuestionManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SubmitQuiz implements BotCommand<SlashCommandInteractionEvent> {

    private final List<Question> questions;

    public SubmitQuiz(List<Question> questions) {
        this.questions = questions;
    }

    @Override
    public String getCommand() {
        return "submitquiz";
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        String subject = event.getOption("subject").getAsString();
        String Q1 = Objects.requireNonNull(event.getOption("q1")).getAsString();
        String Q2 = Objects.requireNonNull(event.getOption("q2")).getAsString();
        String Q3 = Objects.requireNonNull(event.getOption("q3")).getAsString();
        String Q4 = Objects.requireNonNull(event.getOption("q4")).getAsString();
        List<Question> PA = new ArrayList<>();
        List<Question> IP = new ArrayList<>();
        for(Question question : questions){
            if(question.getSubject().equalsIgnoreCase("IP")){
                IP.add(question);
            }else{
                PA.add(question);
            }
        }
        if(subject.equals("PA")) {
            checkAnswers(event, PA, Q1, Q2, Q3, Q4);
        }else{
            checkAnswers(event, IP, Q1, Q2, Q3, Q4);
        }

    }
    public void checkAnswers(SlashCommandInteractionEvent event,List<Question> subQuestions,String Q1,String Q2,String Q3,String Q4){
        StringBuilder sb = new StringBuilder("Your results:\n");
        int count = 1;
        int score = 0;

            for(Question question : subQuestions){
                if(count == 1){
                    sb.append("Q1: You submitted ").append(Q1).append(", the correct answer was ");
                    sb.append(question.getCorrectAnswer());
                    if(Q1.equalsIgnoreCase(question.getCorrectAnswer())){
                        sb.append(" | You were correct! (1p)");
                        score++;
                    }
                    sb.append("\n");
                }else if(count == 2){
                    sb.append("Q2: You submitted ").append(Q2).append(", the correct answer was ");
                    sb.append(question.getCorrectAnswer());
                    if(Q2.equalsIgnoreCase(question.getCorrectAnswer())){
                        sb.append(" | You were correct!");
                        score++;
                    }
                    sb.append("\n");
                }else if(count == 3){
                    sb.append("Q3: You submitted ").append(Q3).append(", the correct answer was ");
                    sb.append(question.getCorrectAnswer());
                    if(Q3.equalsIgnoreCase(question.getCorrectAnswer())){
                        sb.append(" | You were correct!");
                        score++;
                    }
                    sb.append("\n");
                }
                else if(count == 4){
                    sb.append("Q4: You submitted ").append(Q4).append(", the correct answer was ");
                    sb.append(question.getCorrectAnswer());
                    if(Q4.equalsIgnoreCase(question.getCorrectAnswer())){
                        sb.append("  |  You were correct!");
                        score++;
                    }
                    sb.append("\n");
                }
                count++;
            }
        sb.append("Your score: ").append(score).append("/4 points");
        event.reply(String.valueOf(sb)).setEphemeral(true).queue();
    }
    @Override
    public String getDescription() {
        return "This command returns \"Hello!\".";
    }
}