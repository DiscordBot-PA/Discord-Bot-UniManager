package org.example.commands.addGrade;

import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.example.commands.BotCommand;
import org.example.model.Grade;
import org.example.model.Student;
import org.example.repository.GradeRepository;
import org.example.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;

public class AddGrade implements BotCommand<SlashCommandInteractionEvent> {
    private final GradeRepository gradeRepository;
    private final StudentRepository studentRepository;

    public AddGrade(GradeRepository gradeRepository, StudentRepository studentRepository) {
        this.gradeRepository = gradeRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public String getCommand() {
        return "addgrade";
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        List<String> roleNames = new ArrayList<>();

        for (Role role : event.getMember().getRoles()) {
            roleNames.add(role.getName());
        }

        if(roleNames.contains("profesor")) {
            List<String> cursuri = new ArrayList<>();
            cursuri.add("PA");
            cursuri.add("IP");
            if (event.getOption("registrationnumber") != null && event.getOption("value") != null && event.getOption("subject") != null) {

                String registrationNumber = event.getOption("registrationnumber").getAsString();
                int value = event.getOption("value").getAsInt();
                if (value >= 1 && value <= 10) {
                    String subject = event.getOption("subject").getAsString();
                    if (!cursuri.contains(subject)) {
                        event.reply("Course title does not exist!").setEphemeral(true).queue();
                    } else {
                        Student student = studentRepository.findByRegistrationNumber(registrationNumber);
                        if (student != null) {
                            Grade grade = new Grade(value, student, subject);
                            gradeRepository.save(grade);
                        } else {
                            event.reply("The student does not exist.").setEphemeral(true).queue();
                        }

                        event.reply("Grade added to catalog.").setEphemeral(true).queue();
                    }
                } else {
                    event.reply("Grade value must be between 1 and 10.").setEphemeral(true).queue();

                }
            } else {
                event.reply("Error: some options are missing.").setEphemeral(true).queue();
            }
        }else{
            event.reply("You don't have the permission to use this command!").setEphemeral(true).queue();
        }
    }

    @Override
    public String getDescription() {
        return null;
    }
}
