package dev.mlunkeit.afm.ai

import net.dv8tion.jda.api.entities.User
import net.dv8tion.jda.api.entities.channel.Channel

interface LanguageModel
{
    val name: String

    fun prompt(prompt: String, sender: User): String?

    fun prompt(prompt: String, sender: User, channel: Channel): String?

    companion object
    {
        val default: LanguageModel
            get() = GeminiModel
    }
}