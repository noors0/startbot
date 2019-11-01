package pl.noors.startbot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import pl.noors.startbot.listener.MessageReceivedListener;
import pl.noors.startbot.listener.ReadyListener;
import pl.noors.startbot.manager.CommandManager;

import javax.security.auth.login.LoginException;
import java.util.Timer;
import java.util.TimerTask;

public final class Main {

    private static final Loader<CommandManager> COMMAND_MANAGER_LOADER = new Loader<CommandManager>() {
        @Override
        CommandManager load() {
            final CommandManager commandManager = new CommandManager();
            commandManager.loadCommands();
            return commandManager;
        }
    };
    private static final Loader<JDA> JDA_LOADER = new Loader<JDA>() {
        @Override
        JDA load() {
            try {
                return new JDABuilder()
                        .setToken(Config.BOT_TOKEN)
                        .addEventListeners(new MessageReceivedListener())
                        .addEventListeners(new ReadyListener())
                        .build();
            } catch (final LoginException ex) {
                ex.printStackTrace();
                return null;
            }
        }
    };

    public static void main(final String... args) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> JDA_LOADER.getValue().shutdown()));
    }

    public static CommandManager getCommandManager() {
        return COMMAND_MANAGER_LOADER.getValue();
    }

    public static JDA getJda() {
        return JDA_LOADER.getValue();
    }

    public static void updateGame() {
            new Timer().scheduleAtFixedRate(new TimerTask() {
                int i = 0;

                @Override
                public void run() {

                    if (i == 0) {
                        getJda().getPresence().setActivity(Activity.streaming(Config.DESCRIPTION + "" + getJda().getUsers().size() + " użytkowników", "https://twitch.tv/*"));
                    } else if (i == 1) {
                        getJda().getPresence().setActivity(Activity.streaming(Config.DESCRIPTION + "" + getJda().getGuilds().size() + " serwerów", "https://twitch.tv/*"));
                    }

                    i++;

                    if (i > 1)
                        i = 0;
                }
            }, 0, 1 * 20 * 1000);
        }
    }