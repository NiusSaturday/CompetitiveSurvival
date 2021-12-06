package me.lulu.competitivesurvival

import MockBukkitTemplate
import mocks.WorldMockImpl
import org.bukkit.Difficulty
import org.bukkit.World
import org.junit.Assert.assertEquals
import org.junit.Test

class PluginTest : MockBukkitTemplate() {

    @Test
    fun worldSetup_Difficulty() = runWorldSetup {
        assertEquals(Difficulty.HARD, it.difficulty)
    }

    @Test
    fun worldSetup_WorldBorder() = runWorldSetup {
        assertEquals(16.0, it.worldBorder.size, 0.0)
        assertEquals(0.0, it.worldBorder.center.x, 0.0)
        assertEquals(0.0, it.worldBorder.center.y, 0.0)
    }

    private fun runWorldSetup(after: (World) -> Unit) {
        val world = WorldMockImpl()
        plugin.setupWorld(world)

        after.invoke(world)
    }
}
