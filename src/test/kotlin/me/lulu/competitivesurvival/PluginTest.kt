package me.lulu.competitivesurvival

import me.lulu.competitivesurvival.mocks.WorldMockImpl
import org.bukkit.Difficulty
import org.junit.Assert.assertEquals
import org.junit.Test

class PluginTest : MockBukkitTemplate() {

    @Test
    fun testWorldSetup() {
        val world = WorldMockImpl()

        plugin.setupWorld(world)

        assertEquals(Difficulty.HARD, world.difficulty)
        assertEquals(16.0, world.worldBorder.size, 0.0)
        assertEquals(0.0, world.worldBorder.center.x, 0.0)
        assertEquals(0.0, world.worldBorder.center.y, 0.0)
    }
}
