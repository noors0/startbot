package pl.noors.startbot.command;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import pl.noors.startbot.Config;

import java.awt.*;
import java.util.Arrays;

public final class CommandContext {

    private final MessageReceivedEvent event;

    public CommandContext(final MessageReceivedEvent event) {
        this.event = event;
    }

    public Message sendEmbed(final Color color, final String title, final MessageEmbed.Field... fields) {
        final EmbedBuilder embedBuilder = new EmbedBuilder()
                .setColor(color)
                .setTitle(title)
                .setFooter(Config.DESCRIPTION + Config.DISCORD_LINK);
        Arrays.stream(fields).forEach(embedBuilder::addField);
        return this.event.getTextChannel().sendMessage(embedBuilder.build()).complete();
    }

    public boolean kick(final Member member, final String reason) {
        try {
            member.kick(reason).queue();
            return true;
        } catch (final Exception ex) {
            return false;
        }
    }

    public Member getMember() {
        return this.event.getMember();
    }

    public Member getMentionedMember() {
        return !this.event.getMessage().getMentionedMembers().isEmpty() ? this.event.getMessage().getMentionedMembers().get(0) : null;
    }
    public Guild getGuild() {
        return this.event.getGuild();
    }
}
