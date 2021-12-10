package me.lulu.competitivesurvival

import MockBukkitTemplate
import io.kotest.matchers.shouldBe
import org.bukkit.Difficulty

class TestPlugin : MockBukkitTemplate() {

    @Test
    fun worldSetup_Difficulty() {
        Difficulty.HARD shouldBe plugin.gameWorld.difficulty
    }

    @Test
    fun worldSetup_WorldBorder() {
        plugin.gameWorld.worldBorder.size shouldBe 16.0
        plugin.gameWorld.worldBorder.center.x shouldBe 0.0
        plugin.gameWorld.worldBorder.center.y shouldBe 0.0
    }

    @Test
    fun pvp_disableByDefault() = plugin.pvpEnable shouldBe false
}
