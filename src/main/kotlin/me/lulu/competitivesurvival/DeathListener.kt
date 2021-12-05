package me.lulu.competitivesurvival

import br.com.devsrsouza.kotlinbukkitapi.extensions.event.event
import br.com.devsrsouza.kotlinbukkitapi.extensions.event.events
import org.bukkit.event.entity.PlayerDeathEvent

fun CompetitiveSurvival.registerDeathListener() = events {

    event<PlayerDeathEvent> {
        entity.health = Config.RESPAWN_HEALTH
    }

}
