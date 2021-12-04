package me.lulu.competitivesurvival

import org.bukkit.Difficulty
import org.junit.Assert.*
import org.junit.Test

class PluginTest : MockBukkitTemplate() {

    @Test
    fun testPluginSetup() {
        val world = mock.addSimpleWorld("world")
        plugin.setupWorld(world)

        assertEquals(Difficulty.HARD, world.difficulty)
    }

}
