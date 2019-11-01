package pl.noors.startbot.command;

public abstract class Command {

    public CommandInfo getCommandInfo() {
        return this.getClass().isAnnotationPresent(CommandInfo.class) ? this.getClass().getDeclaredAnnotation(CommandInfo.class) : null;
    }

    public abstract void execute(final CommandContext commandContext, final String... args);
}
