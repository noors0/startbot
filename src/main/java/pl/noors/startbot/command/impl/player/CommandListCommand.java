package pl.noors.startbot.command.impl.player;

import net.dv8tion.jda.api.entities.MessageEmbed;
import pl.noors.startbot.Config;
import pl.noors.startbot.command.Command;
import pl.noors.startbot.command.CommandContext;
import pl.noors.startbot.command.CommandInfo;

@CommandInfo(name = "commands", aliases = "komendy")
public final class CommandListCommand extends Command {

    @Override
    public void execute(final CommandContext commandContext, final String... args) {
        commandContext.sendEmbed(Config.SUCCESS_COLOR, Config.DESCRIPTION + "Commands",
                new MessageEmbed.Field("Informacyjne", "**-** `;commands`\n**-** `;help`\n**-** `;info`\n**-** `;serverinfo`", false),
                new MessageEmbed.Field("Administracyjne", "**-** `;ban`\n**-** `;clear`\n**-** `;kick`\n", false));
    }
}
