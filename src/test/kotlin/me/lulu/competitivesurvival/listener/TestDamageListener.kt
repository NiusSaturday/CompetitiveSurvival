package me.lulu.competitivesurvival.listener

import MockDescribeTemplate
import be.seeseemelk.mockbukkit.entity.PlayerMock
import br.com.devsrsouza.kotlinbukkitapi.extensions.server.pluginManager
import io.kotest.core.test.isRootTest
import io.kotest.matchers.shouldBe
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.EntityDamageEvent.DamageCause

class TestDamageListener : MockDescribeTemplate() {

    lateinit var player: PlayerMock

    init {
        beforeTest {
            if (it.isRootTest()) {
                player = mock.addPlayer()
            }
        }

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
}

private fun assertDamageEventCancelled(player: Player, cancelled: Boolean) {
    damageEvent(player).isCancelled shouldBe cancelled
}

private fun damageEvent(player: Player, cause: DamageCause = DamageCause.VOID): EntityDamageEvent {
    val event = EntityDamageEvent(player, cause, 1.0)
    pluginManager.callEvent(event)

    return event
}
