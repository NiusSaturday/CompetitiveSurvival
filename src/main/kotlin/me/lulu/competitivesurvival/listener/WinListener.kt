package me.lulu.competitivesurvival.listener

import br.com.devsrsouza.kotlinbukkitapi.extensions.bukkit.broadcast
import br.com.devsrsouza.kotlinbukkitapi.extensions.event.event
import br.com.devsrsouza.kotlinbukkitapi.extensions.event.events
import br.com.devsrsouza.kotlinbukkitapi.extensions.server.onlinePlayers
import me.lulu.competitivesurvival.CompetitiveSurvival
import me.lulu.competitivesurvival.Config
import org.bukkit.entity.EntityType
import org.bukkit.event.entity.EntityDeathEvent

fun CompetitiveSurvival.registerWinListener() = events {

    event<EntityDeathEvent> {

        if (entityType != EntityType.ENDER_DRAGON)
            return@event


        val killer = entity.killer

        if (killer == null)
            broadcast(Config.WIN_NO_ONE_WINS)
        else
            onlinePlayers.forEach {
                it.sendTitle(Config.WIN_WINNER_TITLE.replace("<killer>", killer.name), Config.WIN_WINNER_SUB)
            }
    }

}
