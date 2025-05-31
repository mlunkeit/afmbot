package dev.mlunkeit.afm.ai

interface LanguageModel
{
    val name: String

    fun prompt(prompt: String, sender: String): String?

    fun prompt(prompt: String, sender: String, channel: Long): String?

    companion object
    {
        val default: LanguageModel
            get() = GeminiModel
    }
}