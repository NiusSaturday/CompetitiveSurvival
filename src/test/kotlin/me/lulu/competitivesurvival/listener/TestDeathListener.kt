package me.lulu.competitivesurvival.listener

import MockBukkitTemplate
import be.seeseemelk.mockbukkit.WorldMock
import io.kotest.matchers.doubles.shouldBeLessThan
import io.kotest.matchers.shouldBe
import me.lulu.competitivesurvival.Config
import mocks.WorldMockImpl
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import kotlin.math.abs

class TestDeathListener : MockBukkitTemplate() {

    private lateinit var player: Player
    private lateinit var world: WorldMock;

    @BeforeEach
    override fun setup() {
        super.setup()

        this.player = mock.addPlayer()
        this.world = WorldMockImpl()
        world.name = Config.WORLD_NAME
        mock.addWorld(world)

        this.player.teleport(world.spawnLocation)
    }

    @Test
    fun playerDeath_KeepInventory() {
        player.inventory.addItem(ItemStack(Material.STONE))

        afterKill {
            player.inventory.isEmpty shouldBe true
        }
    }

    @Test
    fun playerDeath_GainFullHealth() = afterKill {
        player.health shouldBe Config.RESPAWN_HEALTH
    }

    @Test
    fun playerDeath_RandomRespawnInsideWorldBorder() {
        world.worldBorder.size = 10.0

        player.teleport(Location(world, 1000.0, 0.0, 1000.0))

        afterKill {
            val location = player.location

            location.y shouldBe Config.RESPAWN_Y

            abs(location.x) shouldBeLessThan world.worldBorder.size
            abs(location.z) shouldBeLessThan world.worldBorder.size
        }
    }

    @Test
    fun playerDeath_AlwaysRespawnInMainWorld() {
        val anotherWorld = WorldMockImpl()

        player.teleport(anotherWorld.spawnLocation)

        afterKill {
            player.world.name shouldBe Config.WORLD_NAME
        }
    }

    private fun afterKill(checks: () -> Unit) {
        player.health = 0.0
        checks.invoke()
    }

}
