package me.lulu.competitivesurvival.listener

import br.com.devsrsouza.kotlinbukkitapi.extensions.event.event
import br.com.devsrsouza.kotlinbukkitapi.extensions.event.events
import me.lulu.competitivesurvival.CompetitiveSurvival
import me.lulu.competitivesurvival.GameRole
import me.lulu.competitivesurvival.GameState
import org.bukkit.GameMode
import org.bukkit.event.player.PlayerJoinEvent

fun CompetitiveSurvival.registerJoinListener() = events {

    event<PlayerJoinEvent> {
        player.teleport(gameWorld.spawnLocation)

        when (gameState) {
            GameState.WAITING -> {
                player.gameMode = GameMode.ADVENTURE
            }
            GameState.GAMING -> {
                if (roleManager.getRole(player) != GameRole.STAFF)
                    player.health = 0.0
            }
        }
    }

}
