package pl.noors.startbot.listener;

import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import pl.noors.startbot.Main;

import javax.annotation.Nonnull;

public final class ReadyListener extends ListenerAdapter {

    public void onReady(@Nonnull final ReadyEvent event) {
        Main.updateGame();
        System.out.println("Bot jest gotowy!");
    }
}