package me.lulu.competitivesurvival

import io.kotest.matchers.doubles.shouldBeLessThan
import io.kotest.matchers.shouldBe
import org.bukkit.World
import org.bukkit.entity.Player
import kotlin.math.abs

object TestUtils {
    fun isRespawnInsideWorldBorderAndSpecificY(player: Player, world: World) {
        val location = player.location

        location.y shouldBe Config.RESPAWN_Y

        abs(location.x) shouldBeLessThan world.worldBorder.size
        abs(location.z) shouldBeLessThan world.worldBorder.size
    }

    fun afterKill(player: Player, checks: () -> Unit) {
        player.health = 0.0
        checks.invoke()
    }

    fun afterRespawn(player: Player, checks: () -> Unit) {
        player.spigot().respawn()
        checks.invoke()
    }
}
