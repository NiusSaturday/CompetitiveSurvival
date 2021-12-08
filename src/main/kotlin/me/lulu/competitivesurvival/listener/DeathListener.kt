package me.lulu.competitivesurvival

import br.com.devsrsouza.kotlinbukkitapi.extensions.event.event
import br.com.devsrsouza.kotlinbukkitapi.extensions.event.events
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.Player
import org.bukkit.event.entity.PlayerDeathEvent
import kotlin.random.Random

fun CompetitiveSurvival.registerDeathListener() = events {

    event<PlayerDeathEvent> {
        val player = entity

        keepInventory = true
        fullHealth(player)
        randomTpToMainWorldInsideBorder(player)
        addNoClean(player)
    }

}

private fun CompetitiveSurvival.addNoClean(player: Player) {
    noCleanManager.setNoCleanSeconds(player, Config.NO_CLEAN_SECONDS)
}

private fun fullHealth(player: Player) {
    player.health = Config.RESPAWN_HEALTH
}

private fun randomTpToMainWorldInsideBorder(player: Player) {
    val world = Bukkit.getWorld(Config.WORLD_NAME)
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
