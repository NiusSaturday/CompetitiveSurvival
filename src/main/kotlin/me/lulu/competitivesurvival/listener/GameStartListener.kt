package me.lulu.competitivesurvival.listener

import br.com.devsrsouza.kotlinbukkitapi.extensions.event.event
import br.com.devsrsouza.kotlinbukkitapi.extensions.event.events
import br.com.devsrsouza.kotlinbukkitapi.extensions.server.onlinePlayers
import me.lulu.competitivesurvival.CompetitiveSurvival
import me.lulu.competitivesurvival.Config
import me.lulu.competitivesurvival.GameRole
import me.lulu.competitivesurvival.events.GameStartEvent
import org.bukkit.GameMode
import org.bukkit.entity.Player

fun CompetitiveSurvival.registerGameStartListener() = events {

    event<GameStartEvent> {
        onlinePlayers.forEach {
            it.sendTitle(Config.START_GAME_STARTED_TITLE, "")
            changeGamemodeByRole(it)
        }
    }

}

private fun CompetitiveSurvival.changeGamemodeByRole(player: Player) {
    if (roleManager.getRole(player) == GameRole.STAFF)
        player.gameMode = GameMode.CREATIVE
    else
        player.gameMode = GameMode.SURVIVAL
}
