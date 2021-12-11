package me.lulu.competitivesurvival.listener

import MockDescribeTemplate
import be.seeseemelk.mockbukkit.entity.PlayerMock
import be.seeseemelk.mockbukkit.entity.SimpleEntityMock
import br.com.devsrsouza.kotlinbukkitapi.extensions.server.pluginManager
import io.kotest.matchers.shouldBe
import me.lulu.competitivesurvival.Config
import me.lulu.competitivesurvival.GameRole
import me.lulu.competitivesurvival.TestUtils
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerInteractAtEntityEvent
import org.bukkit.util.Vector

//fun CompetitiveSurvival.registerInvSeeListener() = events {
//
//
//
//
//}

class TestInvSeeListener : MockDescribeTemplate() {

    private lateinit var beingClicked: PlayerMock
    private lateinit var clicker: PlayerMock

    override fun beforeEachRoot() {
        super.beforeEachRoot()

        clicker = mock.addPlayer()
        beingClicked = mock.addPlayer()
    }


    init {
        it("Clicker is not staff, nothing happened") {
            fireInteract(clicker, beingClicked)
            clicker.nextMessage() shouldBe null
        }

        describe("Clicker is staff") {
            plugin.roleManager.setRole(clicker, GameRole.STAFF)

            it("Being click is player, show inventory") {
                fireInteract(clicker, beingClicked)
                clicker.nextMessage() shouldBe Config.SPECING_PLAYER_INV.replace("<player>", beingClicked.name)
            }

            it("Click other entity, nothing happend") {
                fireInteract(clicker, SimpleEntityMock(mock))
                clicker.nextMessage() shouldBe null
            }
        }
    }

    fun fireInteract(clicker: Player, beingClicked: Entity) {
        val event = PlayerInteractAtEntityEvent(clicker, beingClicked, Vector.getRandom())
        pluginManager.callEvent(event)
    }

}
