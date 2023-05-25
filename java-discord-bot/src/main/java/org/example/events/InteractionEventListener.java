package org.example.events;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class InteractionEventListener extends ListenerAdapter {
    public String esteProfesor(Member membru) {
        return membru.getRoles().toString();
    }
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        super.onSlashCommandInteraction(event);
        System.out.println("Interaction!");
        switch (event.getName()) {
            case "smecheria" -> event.reply(esteProfesor(event.getMember())).queue();
            case "foamea" -> event.reply("Foamea").queue();
            case "prostia" -> event.reply("Prostia").queue();
            case "nume" -> event.reply(event.getMember().getUser().getName()).queue();
            case "id" -> event.reply(event.getMember().getUser().getId()).queue();
            case "role" -> event.reply((event.getMember()).getRoles().toString());
        }
    }
}
