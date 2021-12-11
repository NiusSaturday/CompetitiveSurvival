package me.lulu.competitivesurvival.listener

import MockDescribeTemplate
import br.com.devsrsouza.kotlinbukkitapi.extensions.server.pluginManager
import io.kotest.matchers.shouldBe
import org.bukkit.event.player.PlayerInteractAtEntityEvent
import org.bukkit.util.Vector

//fun CompetitiveSurvival.registerInvSeeListener() = events {
//
//
//
//
//}

class TestInvSeeListener : MockDescribeTemplate() {


    init {
        describe("Right Click another player") {
            val clicker = mock.addPlayer()
            val beingClicked = mock.addPlayer()

            it("Clicker is not staff, nothing happened") {
                val event = PlayerInteractAtEntityEvent(clicker, beingClicked, Vector.getRandom())
                pluginManager.callEvent(event)

                clicker.nextMessage() shouldBe null
            }
        }
    }

}
