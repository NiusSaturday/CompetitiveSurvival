package me.lulu.competitivesurvival

import br.com.devsrsouza.kotlinbukkitapi.extensions.server.world
import org.bukkit.Difficulty
import org.junit.Test

class PluginTest : MockBukkitTemplate() {

    @Test
    fun testPluginSetup() {
        assert(world(Config.WORLD_NAME)!!.difficulty == Difficulty.HARD)
    }
}
