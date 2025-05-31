package dev.mlunkeit.afm.ai

import com.google.genai.Chat
import com.google.genai.Client
import com.google.genai.types.GenerateContentConfig

object GeminiModel: LanguageModel
{
    override val name = "Gemini"

    private val client = Client()

    private val chats = HashMap<Long, Chat>()

    private fun generatePrompt(prompt: String, sender: String): String
    {
        return """
            Imagine you are a discord bot on a private discord server who is called Friedrich.
            People can interact with you by tagging you. When they tag you, you will respond.
            The preferred language on this server is german, so if no other language appears in the prompt, please respond in german.
            Please don't use the @ chars when talking about people.
            
            You can use emojis too if you want to.

            Respond now to this prompt sent by a user called $sender on the discord server:
            $prompt
        """.trimIndent()
    }

    override fun prompt(prompt: String, sender: String): String?
    {
        val response = client.models.generateContent("gemini-2.0-flash", generatePrompt(prompt, sender), GenerateContentConfig.builder()
            .maxOutputTokens(200)
            .build()
        )

        return response.text()
    }

    override fun prompt(prompt: String, sender: String, channel: Long): String?
    {
        val config = GenerateContentConfig.builder()
            .maxOutputTokens(2000)
            .build()

        if(!chats.containsKey(channel))
            chats.put(channel, client.chats.create("gemini-2.0-flash", config))

        val chat = chats[channel]

        if (chat == null)
            throw RuntimeException("Could not create AI chat")

        try
        {
            return chat.sendMessage(generatePrompt(prompt, sender)).text()
        }
        catch(_: Exception)
        {
            throw RuntimeException("AI did not respond as expected")
        }
    }
}