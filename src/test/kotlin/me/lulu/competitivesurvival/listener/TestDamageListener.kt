package me.lulu.competitivesurvival.listener

import be.seeseemelk.mockbukkit.MockBukkit
import be.seeseemelk.mockbukkit.ServerMock
import be.seeseemelk.mockbukkit.entity.PlayerMock
import br.com.devsrsouza.kotlinbukkitapi.extensions.server.pluginManager
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.core.test.isRootTest
import io.kotest.matchers.shouldBe
import me.lulu.competitivesurvival.CompetitiveSurvival
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.EntityDamageEvent.DamageCause
import java.io.File

class TestDamageListener : DescribeSpec({

    lateinit var player: PlayerMock
    lateinit var mock: ServerMock
    lateinit var plugin: CompetitiveSurvival


    beforeTest {
        if (it.parent?.name?.testName == "setup") {
            mock = MockBukkit.mock()
            plugin = MockBukkit.loadWith(CompetitiveSurvival::class.java, File("src/main/resources/plugin.yml"));
            player = mock.addPlayer()
        }
    }

    afterTest {
        if (it.a.parent?.name?.testName == "setup") {
            MockBukkit.unmock()
        }
    }

    describe("setup") {
        describe("PvP is not enabled") {
            plugin.pvpEnable = false
            val event = damageEvent(player)

            it("Any Damage Should Be Ignored") {
                assertDamageEventCancelled(player, true)
            }
        }

        describe("PvP is enabled") {
            plugin.pvpEnable = true

            it("Default Damage Allowed") {
                assertDamageEventCancelled(player, false)
            }

            describe("Player got no clean") {
                beforeTest {
                    plugin.noCleanManager.setNoCleanSeconds(player, 10)
                }

                afterTest {
                    plugin.noCleanManager.setNoCleanSeconds(player, 0)
                }

                it("Damage should be cancelled") {
                    assertDamageEventCancelled(player, true)
                }
            }
        }
    }
})

private fun assertDamageEventCancelled(player: Player, cancelled: Boolean) {
    damageEvent(player).isCancelled shouldBe cancelled
}

private fun damageEvent(player: Player, cause: DamageCause = DamageCause.VOID): EntityDamageEvent {
    val event = EntityDamageEvent(player, cause, 1.0)
    pluginManager.callEvent(event)

    return event
}
