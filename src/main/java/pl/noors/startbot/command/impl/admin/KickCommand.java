package pl.noors.startbot.command.impl.admin;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import pl.noors.startbot.Config;
import pl.noors.startbot.command.Command;
import pl.noors.startbot.command.CommandContext;
import pl.noors.startbot.command.CommandInfo;

import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@CommandInfo(name = "kick", aliases = "wyrzuc")
public final class KickCommand extends Command {

    @Override
    public void execute(final CommandContext commandContext, final String... args) {
        if (!commandContext.getMember().hasPermission(Permission.MESSAGE_MANAGE)) {
            commandContext.sendEmbed(Config.ERROR_COLOR, "Nie posiadasz uprawnien do tej komendy!");
            return;
        }
        if (args.length == 1) {
            commandContext.sendEmbed(Config.ERROR_COLOR, "Poprawne uzycie: kick <uzytkownik> [powod]");
            return;
        }
        final Member member = commandContext.getMentionedMember();
        if (member == null) {
            commandContext.sendEmbed(Config.ERROR_COLOR, "Nie oznaczyles zadnego uzytkownika lub takowy nie jest na tym serwerze!");
            return;
        }
        final String reason = args.length > 1 ? IntStream.range(2, args.length).mapToObj(i -> args[i] + " ").collect(Collectors.joining()) : "Brak";
        if (!commandContext.kick(member, reason)) {
            commandContext.sendEmbed(Config.ERROR_COLOR, "Nie mozna wyrzucic tego uzytkownika!");
            return;
        }
        final Guild guild = commandContext.getGuild();
        commandContext.sendEmbed(Config.SUCCESS_COLOR, "Pomyslnie wyrzucono uzytkownika " + member.getUser().getAsMention() + " z powodem " + reason + "!").delete().queueAfter(5L, TimeUnit.SECONDS);
        member.getUser().openPrivateChannel().queue((channel) ->
        {
            channel.sendMessage("Zostałeś wyrzucony z serwera **" + guild.getName() + "** z powodem: **" + reason + "**").queue();
        });
    }
}
