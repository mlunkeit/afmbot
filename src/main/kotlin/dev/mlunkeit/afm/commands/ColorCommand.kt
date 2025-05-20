package dev.mlunkeit.afm.commands

import dev.mlunkeit.afm.model.ColorRoleManager
import dev.mlunkeit.afm.model.isColor
import dev.mlunkeit.afm.model.toColor
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent

object ColorCommand
{
    fun onColorCommand(event: SlashCommandInteractionEvent)
    {
        val hex = event.getOption("hex")!!.asString

        try
        {
            if (!hex.isColor())
                throw IllegalArgumentException("Invalid color: $hex")

            val color = hex.toColor()

            val manager = ColorRoleManager(event.member!!)
            manager.changeColor(color)

            event.replyEmbeds(EmbedBuilder()
                    .setColor(color)
                    .setTitle("Farbe geändert")
                    .setDescription("Deine Farbe wurde auf $hex geändert.")
                    .build())
                .setEphemeral(true)
                .queue()
        }
        catch(e: IllegalArgumentException)
        {
            e.printStackTrace()

            event.reply("Dein Farbcode ist ungültig :(")
                .setEphemeral(true)
                .queue()
        }
    }
}