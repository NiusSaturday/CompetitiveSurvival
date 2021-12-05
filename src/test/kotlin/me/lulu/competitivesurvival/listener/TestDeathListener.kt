package me.lulu.competitivesurvival.listener

import me.lulu.competitivesurvival.Config
import MockBukkitTemplate
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class TestDeathListener : MockBukkitTemplate() {

    private lateinit var player: Player

    @Before
    fun loadPlayer() {
        this.player = mock.addPlayer()
    }

    @Test
    fun playerDeath_KeepInventory() {
        player.inventory.addItem(ItemStack(Material.STONE))

        afterKill {
            assertTrue(player.inventory.isEmpty)
        }
    }

    @Test
    fun playerDeath_GainFullHealth() = afterKill {
        assertEquals(player.health, Config.RESPAWN_HEALTH, 0.0)
    }

    private fun afterKill(checks: () -> Unit) {
        player.health = 0.0
        checks.invoke()
    }

}
