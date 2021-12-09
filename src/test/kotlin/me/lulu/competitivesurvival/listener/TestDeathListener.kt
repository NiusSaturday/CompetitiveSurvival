package me.lulu.competitivesurvival.listener

import MockBukkitTemplate
import io.kotest.matchers.shouldBe
import me.lulu.competitivesurvival.TestUtils.afterKill
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class TestDeathListener : MockBukkitTemplate() {

    private lateinit var player: Player

    @BeforeEach
    override fun setup() {
        super.setup()

        this.player = mock.addPlayer()
    }

    @Test
    fun playerDeath_DropInventory() {
        player.inventory.addItem(ItemStack(Material.STONE))

        afterKill(player) {
            player.inventory.isEmpty shouldBe true
        }
    }
}
