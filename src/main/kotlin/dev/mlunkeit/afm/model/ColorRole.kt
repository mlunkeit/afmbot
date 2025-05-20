package dev.mlunkeit.afm.model

import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.entities.Member
import net.dv8tion.jda.api.requests.restaction.RoleAction
import java.awt.Color

class ColorRole(val color: Color, val guild: Guild)
{
    private fun create(): RoleAction
    {
        return guild.createRole()
            .setColor(color)
            .setName(color.createString())
            .setPermissions()
    }

    fun apply(member: Member)
    {
        val role = guild.roles.find { it.name == color.createString() }

        if (role != null)
        {
            guild.addRoleToMember(member, role).queue()
        }
        else
        {
            create().queue { role ->
                guild.addRoleToMember(member, role).queue()
                guild.modifyRolePositions()
                    .selectPosition(role)
                    .moveTo(guild.selfMember.roles.first().position - 1)
                    .queue()
            }
        }
    }

    fun unapply(member: Member)
    {
        val role = guild.roles.find { it.name == color.createString() }

        if (role != null)
        {
            guild.removeRoleFromMember(member, role).queue {
                guild.findMembersWithRoles(role).onSuccess { members ->
                    if (members.isEmpty())
                        role.delete().queue()
                }
            }
        }
    }
}