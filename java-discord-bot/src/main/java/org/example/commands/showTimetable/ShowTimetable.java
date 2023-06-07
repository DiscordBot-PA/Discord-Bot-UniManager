package org.example.commands.showTimetable;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.example.commands.BotCommand;
import org.example.model.Preference;
import org.example.model.TimeSlot;
import org.example.model.Timetable;

import java.util.*;

public class ShowTimetable implements BotCommand<SlashCommandInteractionEvent> {

    private final Timetable timetable;

    public ShowTimetable(Timetable timetable) {
        this.timetable = timetable;
    }

    @Override
    public String getCommand() {
        return "showtimetable";
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        List<TimeSlot> timeSlots = timetable.getSchedule();
        if(timeSlots == null){
            event.reply("The timetable is empty!").setEphemeral(true).queue();
            return;
        }
        Collections.sort(timeSlots, new Comparator<TimeSlot>() {
            @Override
            public int compare(TimeSlot o1, TimeSlot o2) {
                if(o1.getDay().equals(o2.getDay())){
                    return o1.getHour() - o2.getHour();
                }
                return o1.getDay().compareTo(o2.getDay());
            }
        });


        StringBuilder stringBuilder = new StringBuilder();
        for(TimeSlot timeSlot : timeSlots){
            stringBuilder.append(timeSlot.getDay()).append(" ").append(timeSlot.getHour()).append(":00 - ").append(timeSlot.getProfessor()).append(" - Group ").append(timeSlot.getGroup()).append(" - Subject: ").append(timeSlot.getSubject()).append("\n");
        }
        event.reply(stringBuilder.toString()).setEphemeral(true).queue();
    }

    @Override
    public String getDescription() {
        return "Shows the timetable.";
    }

}
