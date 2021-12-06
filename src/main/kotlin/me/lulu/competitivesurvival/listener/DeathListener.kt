package me.lulu.competitivesurvival

import br.com.devsrsouza.kotlinbukkitapi.extensions.event.event
import br.com.devsrsouza.kotlinbukkitapi.extensions.event.events
import org.bukkit.Location
import org.bukkit.entity.Player
import org.bukkit.event.entity.PlayerDeathEvent
import kotlin.random.Random

fun CompetitiveSurvival.registerDeathListener() = events {

    event<PlayerDeathEvent> {
        val player = entity

        fullHealth(player)
        randomTpInsideBorder(player)
    }

}

private fun fullHealth(player: Player) {
    player.health = Config.RESPAWN_HEALTH
}

private fun randomTpInsideBorder(player: Player) {
    val world = player.world
    val radius = world.worldBorder.size

    player.teleport(
        Location(
            world,
            Random.nextDouble(-radius, radius),
            Config.RESPAWN_Y,
            Random.nextDouble(-radius, radius)
        )
    )
}
