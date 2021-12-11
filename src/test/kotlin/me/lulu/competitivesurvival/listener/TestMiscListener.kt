package me.lulu.competitivesurvival.listener

import MockDescribeTemplate
import br.com.devsrsouza.kotlinbukkitapi.extensions.server.pluginManager
import io.kotest.matchers.shouldBe
import me.lulu.competitivesurvival.GameState
import org.bukkit.event.entity.FoodLevelChangeEvent

class TestMiscListener : MockDescribeTemplate() {


    init {
        describe("Waiting State") {
            plugin.gameState = GameState.WAITING

            it("Prevent losing hunger") {
                assertHungerChange(10, 20)
            }
        }

        describe("Gaming State") {
            plugin.gameState = GameState.GAMING

            it("Should losing hunger") {
                assertHungerChange(10, 10)
            }
        }
    }

    fun assertHungerChange(change: Int, excepted: Int) {
        val player = mock.addPlayer()

        val event = FoodLevelChangeEvent(player, change)
        pluginManager.callEvent(event)

        event.foodLevel shouldBe excepted
    }

}
