package pl.noors.startbot.listener;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import pl.noors.startbot.Main;
import pl.noors.startbot.command.CommandContext;

import javax.annotation.Nonnull;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class MessageReceivedListener extends ListenerAdapter {

    private final ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() > 1 ? Runtime.getRuntime().availableProcessors() / 2 : 1);

    @Override
    public void onMessageReceived(@Nonnull final MessageReceivedEvent event) {
        this.executorService.execute(() -> {
            final String[] args = event.getMessage().getContentDisplay().split(" ");
            if (args[0].startsWith(";")) try {
                Main.getCommandManager().getCommand(args[0].split(";")[1]).ifPresent(command -> command.execute(new CommandContext(event), args));
            } catch (final Exception ignored) {}
        });
    }
}