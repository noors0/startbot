package pl.noors.startbot.command.impl.player;

import net.dv8tion.jda.api.entities.MessageEmbed;
import pl.noors.startbot.Config;
import pl.noors.startbot.Main;
import pl.noors.startbot.command.Command;
import pl.noors.startbot.command.CommandContext;
import pl.noors.startbot.command.CommandInfo;

import java.lang.management.ManagementFactory;
import java.util.concurrent.TimeUnit;

@CommandInfo(name = "info", aliases = "informacje")
public final class InfoCommand extends Command {

    @Override
    public void execute(final CommandContext commandContext, final String... args) {
        commandContext.sendEmbed(Config.SUCCESS_COLOR, Config.DESCRIPTION + "Info",
                new MessageEmbed.Field("Prefix", "**;**", true),
                new MessageEmbed.Field("Wersja", "**0.1**", true),
                new MessageEmbed.Field("Uptime", this.getTime(), true),
                new MessageEmbed.Field("Ilość Serwerów: ", String.valueOf(Main.getJda().getGuilds().size()), true),
                new MessageEmbed.Field("Ilość Użytkowników: ", String.valueOf(Main.getJda().getUsers().size()), true));
    }

    private String getTime() {
        final long time = ManagementFactory.getRuntimeMXBean().getUptime();
        return TimeUnit.MILLISECONDS.toDays(time) + "d, " + TimeUnit.MILLISECONDS.toHours(time) + "g, " + TimeUnit.MILLISECONDS.toMinutes(time) % 60 + "m";
    }
}
