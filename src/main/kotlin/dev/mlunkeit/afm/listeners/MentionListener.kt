package dev.mlunkeit.afm.listeners

import dev.mlunkeit.afm.ai.LanguageModel
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

object MentionListener: ListenerAdapter()
{
    override fun onMessageReceived(event: MessageReceivedEvent)
    {
        if (!event.isFromGuild)
            return

        if (!event.message.mentions.isMentioned(event.jda.selfUser))
            return

        if (event.message.author.isBot)
            return

        val response = LanguageModel.default.prompt(event.message.contentDisplay, event.message.author.effectiveName, event.channel.idLong)

        if (response != null)
            event.message.reply(response).queue()
    }
}