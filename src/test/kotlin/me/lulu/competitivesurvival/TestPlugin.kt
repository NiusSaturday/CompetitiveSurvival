package me.lulu.competitivesurvival

import MockBukkitTemplate
import io.kotest.matchers.shouldBe
import mocks.WorldMockImpl
import org.bukkit.Difficulty
import org.bukkit.World

class TestPlugin : MockBukkitTemplate() {

    @Test
    fun worldSetup_Difficulty() = runWorldSetup {
        Difficulty.HARD shouldBe it.difficulty
    }

    @Test
    fun worldSetup_WorldBorder() = runWorldSetup {
        it.worldBorder.size shouldBe 16.0
        it.worldBorder.center.x shouldBe 0.0
        it.worldBorder.center.y shouldBe 0.0
    }

    @Test
    fun pvp_disableByDefault() = plugin.pvpEnable shouldBe false

    private fun runWorldSetup(after: (World) -> Unit) {
        val world = WorldMockImpl()
        plugin.setupWorld(world)

        after.invoke(world)
    }
}
