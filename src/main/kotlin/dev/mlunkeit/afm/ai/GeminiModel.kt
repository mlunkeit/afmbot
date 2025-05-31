package dev.mlunkeit.afm.ai

import com.google.genai.Chat
import com.google.genai.Client
import com.google.genai.types.GenerateContentConfig
import net.dv8tion.jda.api.entities.User
import net.dv8tion.jda.api.entities.channel.Channel

object GeminiModel: LanguageModel
{
    override val name = "Gemini"

    private val client = Client()

    private val chat: Chat = client.chats.create("gemini-2.0-flash", GenerateContentConfig.builder()
        .maxOutputTokens(200)
        .build()
    )

    private fun generatePrompt(prompt: String, sender: User, channel: Channel?): String
    {
        return """
            Imagine you are a discord bot on a private discord server who is called Friedrich.
            People can interact with you by tagging you. When they tag you, you will respond.
            The preferred language on this server is german, so if no other language appears in the prompt, please respond in german.
            Please don't use the @ chars when talking about people. Please don't use their IDs either. Use only their names when talking about them.
            You can use emojis too if you want to.
            
            Some additional information about the user and channel you need to respond to:
            
            User Name: ${sender.effectiveName}
            User ID: ${sender.id}
            Channel Name: ${channel?.name}
            Channel ID: ${channel?.id}

            Respond now to this prompt:
            
            $prompt
        """.trimIndent()
    }

    override fun prompt(prompt: String, sender: User): String?
    {
        val response = client.models.generateContent("gemini-2.0-flash", generatePrompt(prompt, sender, null), GenerateContentConfig.builder()
            .maxOutputTokens(200)
            .build()
        )

        return response.text()
    }

    override fun prompt(prompt: String, sender: User, channel: Channel): String?
    {
        try
        {
            return chat.sendMessage(generatePrompt(prompt, sender, channel)).text()
        }
        catch(e: Exception)
        {
            e.printStackTrace()

            throw RuntimeException("AI did not respond as expected")
        }
    }
}