package com.gbf.bot.events;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.events.user.update.UserUpdateOnlineStatusEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class EventListener extends ListenerAdapter {
    @Override
    public void onMessageReactionAdd(@NotNull MessageReactionAddEvent event) {
        String user = event.getUser().getName();
        Emoji emoji = event.getReaction().getEmoji();
        String jump = event.getJumpUrl();

        String message = "GBF Java Edition has detected a reaction add in " + jump + " by " + user + "\nEmoji: " + emoji.getFormatted();

        event.getChannel().asTextChannel().sendMessage(message).queue();
    }

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        System.out.println("GBF is now online!");
    }

    @Override
    public void onUserUpdateOnlineStatus(UserUpdateOnlineStatusEvent event) {
        User user = event.getUser();
        String message = user.getName() + " updated their online status! [GBF Java Edition]";
        TextChannel channel = user.getJDA().getTextChannelById("");

        System.out.println(message);

        if (channel != null) channel.sendMessage(message).queue();


    }
}
