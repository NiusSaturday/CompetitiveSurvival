package me.lulu.competitivesurvival.listener

import be.seeseemelk.mockbukkit.MockBukkit
import be.seeseemelk.mockbukkit.ServerMock
import be.seeseemelk.mockbukkit.entity.PlayerMock
import br.com.devsrsouza.kotlinbukkitapi.extensions.server.pluginManager
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.core.test.isRootTest
import io.kotest.matchers.shouldBe
import me.lulu.competitivesurvival.CompetitiveSurvival
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageEvent
import java.io.File

class TestDamageListener : BehaviorSpec({

    lateinit var player: PlayerMock
    lateinit var mock: ServerMock
    lateinit var plugin: CompetitiveSurvival

    beforeTest {
        if (it.isRootTest()) {
            mock = MockBukkit.mock()
            plugin = MockBukkit.loadWith(CompetitiveSurvival::class.java, File("src/main/resources/plugin.yml"));
            player = mock.addPlayer()
        }
    }

    afterTest {
        if (it.a.isRootTest()) {
            MockBukkit.unmock()
        }
    }

    given("setup") {
        When("PvP is not enabled") {
            plugin.pvpEnable = false
            val event = damageEvent(player)

            then("Any Damage Should Be Ignored") {
                event.isCancelled shouldBe true
            }
        }
    }
})

private fun damageEvent(player: Player): EntityDamageEvent {
    val event = EntityDamageEvent(player, EntityDamageEvent.DamageCause.CONTACT, 1.0)
    pluginManager.callEvent(event)

    return event
}
