package pl.noors.startbot;

import java.awt.*;

public final class Config {

    static final String BOT_TOKEN = "you bot token";

    public static final String DESCRIPTION = "StartBot | ";

    public static final String INVITE_LINK = "https://discordapp.com/api/oauth2/authorize?client_id=" + Main.getJda().getSelfUser().getId() + "&permissions=8&scope=bot";

    public static final String DISCORD_LINK = "you discord link";

    public static final Color SUCCESS_COLOR = new Color(34, 227, 85);

    public static final Color ERROR_COLOR = new Color(227, 34, 34);
}
