package me.lulu.competitivesurvival.listener

import me.lulu.competitivesurvival.Config
import MockBukkitTemplate
import be.seeseemelk.mockbukkit.WorldMock
import mocks.WorldMockImpl
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.World
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class TestDeathListener : MockBukkitTemplate() {

    private lateinit var player: Player
    private lateinit var world: WorldMock;

    @Before
    fun loadPlayer() {
        this.player = mock.addPlayer()
        this.world = WorldMockImpl()

        this.player.teleport(world.spawnLocation)
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
        assertEquals(Config.RESPAWN_HEALTH, player.health, 0.0)
    }

    @Test
    fun playerDeath_RandomRespawnInsideWorldBorder() {
        world.worldBorder.size = 10.0

        player.teleport(Location(world, 1000.0, 0.0, 1000.0))

        afterKill {
            assertEquals(Config.RESPAWN_Y, player.location.y, 0.0)
            assertTrue(world.worldBorder.isInside(player.location))
        }
    }

    @Test
    fun playerDeath_AlwaysRespawnInMainWorld() {

    }

    //todo 死在終界跟地獄的 Test


    private fun afterKill(checks: () -> Unit) {
        player.health = 0.0
        checks.invoke()
    }

}
