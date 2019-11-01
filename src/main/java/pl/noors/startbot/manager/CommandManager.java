package pl.noors.startbot.manager;

import pl.noors.startbot.command.Command;
import pl.noors.startbot.command.impl.admin.KickCommand;
import pl.noors.startbot.command.impl.player.CommandListCommand;
import pl.noors.startbot.command.impl.player.HelpCommand;
import pl.noors.startbot.command.impl.player.InfoCommand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public final class CommandManager {

    private final List<Command> commands = new ArrayList<>();

    public void loadCommands() {
        this.commands.add(new HelpCommand());
        this.commands.add(new InfoCommand());
        this.commands.add(new CommandListCommand());

        this.commands.add(new KickCommand());
    }

    public Optional<Command> getCommand(final String arg) {
        return this.commands
                .stream()
                .filter(command -> command.getCommandInfo() != null && command.getCommandInfo().name().equalsIgnoreCase(arg) || Arrays.asList(command.getCommandInfo().aliases()).contains(arg.toLowerCase()))
                .findFirst();
    }
}
