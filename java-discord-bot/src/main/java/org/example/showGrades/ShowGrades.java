package org.example.showGrades;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.example.commands.BotCommand;
import org.example.model.Grade;
import org.example.model.Student;
import org.example.repository.GradeRepository;
import org.example.repository.StudentRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ShowGrades implements BotCommand<SlashCommandInteractionEvent> {

    private final GradeRepository gradeRepository;
    private final StudentRepository studentRepository;


    public ShowGrades(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
        gradeRepository = new GradeRepository();
    }

    @Override
    public String getCommand() {
        return "showgrades";
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        if (event.getOption("registrationnumber") != null) {
            String registrationNumber = event.getOption("registrationnumber").getAsString();
            Student student = studentRepository.findByRegistrationNumber(registrationNumber);
            if (student != null) {
                List<Grade> grades = gradeRepository.getGrades(registrationNumber);
                if (grades != null) {
                    StringBuilder gradesMessage = new StringBuilder("Student " + registrationNumber + " grades:\n");
                    Map<String, List<Integer>> subjectGrades = new HashMap<>();
                    for (Grade grade : grades) {
                        String subjectTitle = grade.getSubjectTitle();
                        int value = grade.getValue();
                        if (subjectGrades.containsKey(subjectTitle)) {
                            subjectGrades.get(subjectTitle).add(value);
                        } else {
                            List<Integer> values = new ArrayList<>();
                            values.add(value);
                            subjectGrades.put(subjectTitle, values);
                        }
                    }
                    for (Map.Entry<String, List<Integer>> entry : subjectGrades.entrySet()) {
                        String subjectTitle = entry.getKey();
                        List<Integer> values = entry.getValue();
                        gradesMessage.append(subjectTitle).append(": ").append(String.join(", ", values.stream().map(String::valueOf).collect(Collectors.toList()))).append("\n");
                    }
                    event.reply(gradesMessage.toString()).setEphemeral(true).queue();
                } else {
                    event.reply("The student does not have grades.").setEphemeral(true).queue();
                }
            } else {
                event.reply("The student does not exist.").setEphemeral(true).queue();
            }

        } else {
            event.reply("Error: some options are missing.").setEphemeral(true).queue();
        }
    }

    @Override
    public String getDescription() {
        return null;
    }
}
