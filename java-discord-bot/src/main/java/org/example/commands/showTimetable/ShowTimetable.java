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
        //a function that sort the timetable by day and hour
        List<TimeSlot> timeSlots = timetable.getSchedule();
        Collections.sort(timeSlots, new Comparator<TimeSlot>() {
            @Override
            public int compare(TimeSlot o1, TimeSlot o2) {
                if(o1.getDay().equals(o2.getDay())){
                    return o1.getHour() - o2.getHour();
                }
                return o1.getDay().compareTo(o2.getDay());
            }
        });
        //a string builder that concatenates the timetable lines like this Tuesday 10:00 - tudstk - Group A5 - Subject: PA
        //Tuesday 8:00 - George - Group B4 - Subject: IP
        //Monday 10:00 - George - Group Course - Subject: IP
        //Monday 8:00 - tudstk - Group B4 - Subject: PA
        //Monday 12:00 - tudstk - Group Course - Subject: PA
        //Tuesday 12:00 - George - Group A5 - Subject: IP

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
