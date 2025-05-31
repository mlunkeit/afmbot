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

        try
        {
            val response = LanguageModel.default.prompt(event.message.contentDisplay, event.message.author, event.channel)

            if (response != null)
                event.message.reply(response).queue()
        }
        catch(_: RuntimeException)
        {
            event.message.reply("Das Sprachmodell hat mit einem Fehler geantwortet :sob:").queue()
        }
    }
}