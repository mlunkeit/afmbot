package dev.mlunkeit.afm.commands

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent

object DebugCommand
{
    fun onDebugCommand(event: SlashCommandInteractionEvent)
    {
        event.member!!.roles.forEach { role ->
            if (role.name.toIntOrNull(16) != null) {
                role.delete().queue()
            }
        }

        event.reply("Debugging complete!").setEphemeral(true).queue()
    }
}