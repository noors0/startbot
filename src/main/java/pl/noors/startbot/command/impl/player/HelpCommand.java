package pl.noors.startbot.command.impl.player;

import net.dv8tion.jda.api.entities.MessageEmbed;
import pl.noors.startbot.Config;
import pl.noors.startbot.command.Command;
import pl.noors.startbot.command.CommandContext;
import pl.noors.startbot.command.CommandInfo;

@CommandInfo(name = "help", aliases = "pomoc")
public final class HelpCommand extends Command {

    @Override
    public void execute(final CommandContext commandContext, final String... args) {
        commandContext.sendEmbed(Config.SUCCESS_COLOR, Config.DESCRIPTION + "Help",
                new MessageEmbed.Field("Komendy", "**;commands**", true),
                new MessageEmbed.Field("Wersja", "**0.1**", true),
                new MessageEmbed.Field("Informacje", "**;info**", true),
                new MessageEmbed.Field("Serwer Pomocy", "[Kliknij](" + Config.DISCORD_LINK + ")", true),
                new MessageEmbed.Field("Prefix", "**;**", true),
                new MessageEmbed.Field("Zaproszenie bota", "[Kliknij](" + Config.INVITE_LINK + ")", true));
    }
}
