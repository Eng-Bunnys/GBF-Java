package com.gbf.bot;

import com.gbf.bot.events.EventListener;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

import javax.security.auth.login.LoginException;

public class GBFClient {

    private final Dotenv config;
    private final ShardManager shardManager;

    public GBFClient() throws LoginException {
        config = Dotenv.configure().ignoreIfMissing().load();
        String token = config.get("TOKEN");

        DefaultShardManagerBuilder client = DefaultShardManagerBuilder.createDefault(token);
        client.enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_MESSAGE_REACTIONS, GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_EMOJIS_AND_STICKERS, GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_PRESENCES);
        client.setActivity(Activity.playing("Java 17"));

        shardManager = client.build();
        shardManager.addEventListener(new EventListener());
    }

    public ShardManager getShardManager() {
        return shardManager;
    }

    public Dotenv getConfig() {
        return config;
    }

    public static void main(String[] args) throws LoginException {
        try {
            GBFClient client = new GBFClient();
        } catch (LoginException err) {
            System.out.println("Error: " + err);
        }
    }
}
