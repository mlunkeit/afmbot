package dev.mlunkeit.afm.listeners

import dev.mlunkeit.afm.commands.ColorCommand
import dev.mlunkeit.afm.commands.DebugCommand
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

object CommandListener: ListenerAdapter()
{
    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent)
    {
        when (event.name)
        {
            "color" -> ColorCommand.onColorCommand(event)
            "debug" -> DebugCommand.onDebugCommand(event)
        }
    }
}