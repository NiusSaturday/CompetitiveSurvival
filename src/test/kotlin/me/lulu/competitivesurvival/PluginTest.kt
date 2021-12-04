package me.lulu.competitivesurvival

import br.com.devsrsouza.kotlinbukkitapi.extensions.server.defaultGameMode
import br.com.devsrsouza.kotlinbukkitapi.extensions.server.isHardcore
import br.com.devsrsouza.kotlinbukkitapi.extensions.server.worlds
import org.bukkit.Difficulty
import org.junit.Test

class PluginTest : MockBukkitTemplate() {

    @Test
    fun testPluginSetup() {
        assert(worlds[0].difficulty == Difficulty.HARD)
    }
}
