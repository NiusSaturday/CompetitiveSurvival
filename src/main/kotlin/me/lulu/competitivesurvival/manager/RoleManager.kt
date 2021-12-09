package me.lulu.competitivesurvival.manager

import br.com.devsrsouza.kotlinbukkitapi.architecture.lifecycle.LifecycleListener
import me.lulu.competitivesurvival.CompetitiveSurvival
import me.lulu.competitivesurvival.GameRole
import org.bukkit.entity.Player
import java.util.*

class RoleManager(override val plugin: CompetitiveSurvival) : LifecycleListener<CompetitiveSurvival> {

    private val roles = mutableMapOf<UUID, GameRole>()

    fun setRole(player: Player, role: GameRole) {
        roles[player.uniqueId] = role
    }

    fun getRole(player: Player): GameRole {
        return roles.getOrDefault(player.uniqueId, GameRole.PLAYER)
    }

}
