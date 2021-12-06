package me.lulu.competitivesurvival.listener

import MockBukkitTemplate
import be.seeseemelk.mockbukkit.entity.PlayerMock
import br.com.devsrsouza.kotlinbukkitapi.extensions.server.pluginManager
import io.kotest.matchers.shouldBe
import org.bukkit.event.entity.EntityDamageEvent

class TestDamageListener : MockBukkitTemplate() {

    private lateinit var player: PlayerMock

    @BeforeEach
    override fun setup() {
        super.setup()

        player = mock.addPlayer()
    }

    @Test
    fun playerDamage_CancelIfNoClean() {
        val event = EntityDamageEvent(player, EntityDamageEvent.DamageCause.CONTACT, 1.0)
        plugin.noCleanManager.setNoCleanSeconds(player, 1)

        pluginManager.callEvent(event)

        event.isCancelled shouldBe true
    }

}
