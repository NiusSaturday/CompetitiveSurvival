package me.lulu.competitivesurvival.listener

import br.com.devsrsouza.kotlinbukkitapi.extensions.event.event
import br.com.devsrsouza.kotlinbukkitapi.extensions.event.events
import me.lulu.competitivesurvival.CompetitiveSurvival
import me.lulu.competitivesurvival.Config
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerRespawnEvent
import kotlin.random.Random


fun CompetitiveSurvival.registerRespawnListener() = events {
    event<PlayerRespawnEvent> {
        fullHealth(player)
        addNoClean(player)

        respawnLocation = getRandomLocationInsideBorderInMainWorld()
    }
}


private fun CompetitiveSurvival.addNoClean(player: Player) {
    noCleanManager.setNoCleanSeconds(player, Config.NO_CLEAN_SECONDS)
}

private fun fullHealth(player: Player) {
    player.health = Config.RESPAWN_HEALTH
}

private fun getRandomLocationInsideBorderInMainWorld(): Location {
    val world = Bukkit.getWorld(Config.WORLD_NAME)
    val radius = world.worldBorder.size

    return Location(
        world,
        Random.nextDouble(-radius, radius),
        Config.RESPAWN_Y,
        Random.nextDouble(-radius, radius)
    )
}
