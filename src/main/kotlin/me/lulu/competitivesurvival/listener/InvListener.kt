package me.lulu.competitivesurvival.listener

import br.com.devsrsouza.kotlinbukkitapi.extensions.event.event
import br.com.devsrsouza.kotlinbukkitapi.extensions.event.events
import br.com.devsrsouza.kotlinbukkitapi.extensions.text.msg
import me.lulu.competitivesurvival.CompetitiveSurvival
import me.lulu.competitivesurvival.Config
import me.lulu.competitivesurvival.GameRole
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerInteractAtEntityEvent

fun CompetitiveSurvival.registerInvSeeListener() = events {

    event<PlayerInteractAtEntityEvent>() {
        if (rightClicked !is Player)
            return@event

        val beingClicked = rightClicked as Player

        if (roleManager.getRole(player) == GameRole.STAFF) {
            player.msg(Config.SPECING_PLAYER_INV.replace("<player>", beingClicked.name))
            player.performCommand("spi open inv ${beingClicked.name}")
        }
    }

}
