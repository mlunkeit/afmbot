package dev.mlunkeit.afm.model

import net.dv8tion.jda.api.entities.Member
import java.awt.Color

class ColorRoleManager(val member: Member)
{
    fun currentColorRole(): ColorRole?
    {
        val role = member.roles
            .find { role -> role.name.isColor() }
            ?: return null

        return ColorRole(role.color ?: return null, member.guild)
    }

    fun changeColor(color: Color)
    {
        resetColor()

        val new = ColorRole(color, member.guild)
        new.apply(member)
    }

    fun resetColor()
    {
        currentColorRole()?.unapply(member)
    }
}