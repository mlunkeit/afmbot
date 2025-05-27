package dev.mlunkeit.afm.model

import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.entities.Member
import net.dv8tion.jda.api.entities.Role
import java.awt.Color

class ColorRole(val color: Color, val guild: Guild)
{
    private fun create(callback: (role: Role) -> Unit)
    {
        return guild.createRole()
            .setColor(color)
            .setName(color.createString())
            .setPermissions()
            .queue { role ->
                guild.modifyRolePositions()
                    .selectPosition(role)
                    .moveBelow(guild.botRole!!)
                    .queue { callback(role) }
            }
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
            create { role -> guild.addRoleToMember(member, role).queue() }
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