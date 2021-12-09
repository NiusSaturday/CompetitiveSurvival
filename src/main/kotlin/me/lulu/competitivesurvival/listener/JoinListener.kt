package me.lulu.competitivesurvival.listener

import br.com.devsrsouza.kotlinbukkitapi.extensions.event.event
import br.com.devsrsouza.kotlinbukkitapi.extensions.event.events
import me.lulu.competitivesurvival.CompetitiveSurvival
import org.bukkit.GameMode
import org.bukkit.event.player.PlayerJoinEvent

fun CompetitiveSurvival.registerJoinListener() = events {

    event<PlayerJoinEvent> {
        player.gameMode = GameMode.ADVENTURE
    }

}
