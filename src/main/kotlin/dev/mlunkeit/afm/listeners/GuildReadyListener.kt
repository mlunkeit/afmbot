package dev.mlunkeit.afm.listeners

import net.dv8tion.jda.api.events.guild.GuildReadyEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.interactions.commands.OptionType
import net.dv8tion.jda.api.interactions.commands.build.Commands
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData

object GuildReadyListener: ListenerAdapter()
{
    override fun onGuildReady(event: GuildReadyEvent)
    {
        event.guild.updateCommands().addCommands(
            Commands.slash("color", "Ändert die Farbe deines Namens")
                .addSubcommands(
                    SubcommandData("set", "Setzt die Farbe deines Namens")
                        .addOption(OptionType.STRING, "hex", "Der RGB Wert der Farbe in Hexadezimal", true),
                    SubcommandData("reset", "Setzt die Farbe deines Namens auf den Standard zurück")
                )
        ).queue()
    }
}