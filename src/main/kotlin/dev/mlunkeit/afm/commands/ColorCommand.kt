package dev.mlunkeit.afm.commands

import dev.mlunkeit.afm.model.ColorRoleManager
import dev.mlunkeit.afm.model.isColor
import dev.mlunkeit.afm.model.toColor
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent

object ColorCommand
{
    fun onColorCommand(event: SlashCommandInteractionEvent)
    {
        if (event.subcommandName == "set")
        {
            val hex = event.getOption("hex")!!.asString.uppercase()

            try
            {
                if (!hex.isColor())
                    throw IllegalArgumentException("Invalid color: $hex")

                val color = hex.toColor()

                val manager = ColorRoleManager(event.member!!)
                manager.changeColor(color)

                event.reply("Deine Farbe wurde erfolgreich geändert :moyai:")
                    .setEphemeral(true)
                    .queue()
            }
            catch(e: IllegalArgumentException)
            {
                e.printStackTrace()

                event.reply("Aur naur, dein Farbcode ist ungültig :sob:")
                    .setEphemeral(true)
                    .queue()
            }
        }
        else if (event.subcommandName == "reset")
        {
            val manager = ColorRoleManager(event.member!!)
            manager.resetColor()

            event.reply("Deine Farbe wurde zurückgesetzt :cry:")
                .setEphemeral(true)
                .queue()
        }
    }
}