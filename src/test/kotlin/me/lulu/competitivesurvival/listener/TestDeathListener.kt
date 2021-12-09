package me.lulu.competitivesurvival.listener

import MockBukkitTemplate
import be.seeseemelk.mockbukkit.WorldMock
import io.kotest.matchers.shouldBe
import me.lulu.competitivesurvival.Config
import me.lulu.competitivesurvival.TestUtils
import me.lulu.competitivesurvival.TestUtils.afterKill
import mocks.WorldMockImpl
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

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
    fun playerDeath_DropInventory() {
        player.inventory.addItem(ItemStack(Material.STONE))

        afterKill(player) {
            player.inventory.isEmpty shouldBe true
        }
    }

    @Test
    fun playerDeath_GainFullHealth() = afterKill(player) {
        player.health shouldBe Config.RESPAWN_HEALTH
    }

    @Test
    fun playerDeath_RandomRespawnInsideWorldBorder() {
        world.worldBorder.size = 10.0
        player.teleport(Location(world, 1000.0, 0.0, 1000.0))

        afterKill(player) {
            TestUtils.isRespawnInsideWorldBorderAndSpecificY(player, world)
        }
    }

    @Test
    fun playerDeath_AlwaysRespawnInMainWorld() {
        val anotherWorld = WorldMockImpl()

        player.teleport(anotherWorld.spawnLocation)

        afterKill(player) {
            player.world.name shouldBe Config.WORLD_NAME
        }
    }

    @Test
    fun playerDeath_NoClean() = afterKill(player) {
        val timeUntilNoCleanEnds = plugin.noCleanManager.getNoCleanEnds(player)
        val exceptedTime = System.currentTimeMillis() + (Config.NO_CLEAN_SECONDS * 1000)

        timeUntilNoCleanEnds shouldBe exceptedTime
    }

}
