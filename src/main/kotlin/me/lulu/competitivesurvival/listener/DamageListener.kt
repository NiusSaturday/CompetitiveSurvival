package me.lulu.competitivesurvival.listener

import br.com.devsrsouza.kotlinbukkitapi.extensions.event.event
import br.com.devsrsouza.kotlinbukkitapi.extensions.event.events
import me.lulu.competitivesurvival.CompetitiveSurvival
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageEvent

fun CompetitiveSurvival.registerDamageListener() = events {

    event<EntityDamageEvent> {
        if (entity !is Player)
            return@event

        val player: Player = entity as Player

        if (noCleanManager.isNoCleaning(player))
            isCancelled = true

        if (!pvpEnable)
            isCancelled = true
    }

}
