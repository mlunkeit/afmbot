package dev.mlunkeit.afm.listeners

import net.dv8tion.jda.api.events.guild.GuildReadyEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.interactions.commands.OptionType
import net.dv8tion.jda.api.interactions.commands.build.Commands

object GuildReadyListener: ListenerAdapter()
{
    override fun onGuildReady(event: GuildReadyEvent)
    {
        event.guild.updateCommands().addCommands(
            Commands.slash("color", "Ändert die Farbe deines Namens")
                .addOption(OptionType.STRING, "hex", "Der RGB Wert der Farbe in Hexadezimal", true),
            Commands.slash("debug", "Versucht auftretende Bugs zu beheben")
        ).queue()
    }
}