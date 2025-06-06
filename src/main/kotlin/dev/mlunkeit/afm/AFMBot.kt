package dev.mlunkeit.afm

import dev.mlunkeit.afm.listeners.CommandListener
import dev.mlunkeit.afm.listeners.GuildReadyListener
import dev.mlunkeit.afm.listeners.MentionListener
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.OnlineStatus
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.requests.GatewayIntent

fun main()
{
    val token = System.getenv("JDA_TOKEN")

    val jda = JDABuilder.createDefault(token)
        .enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.MESSAGE_CONTENT)
        .setStatus(OnlineStatus.IDLE)
        .setActivity(Activity.watching("you"))
        .addEventListeners(GuildReadyListener, CommandListener, MentionListener)
        .build()

    Runtime.getRuntime().addShutdownHook(Thread {
        jda.shutdownNow()
    })
}