package me.lulu.competitivesurvival.listener

import MockDescribeTemplate
import br.com.devsrsouza.kotlinbukkitapi.extensions.server.pluginManager
import io.kotest.matchers.shouldBe
import me.lulu.competitivesurvival.Config
import me.lulu.competitivesurvival.GameRole
import me.lulu.competitivesurvival.events.GameStartEvent
import org.bukkit.Difficulty
import org.bukkit.GameMode

class TestGameStartListener : MockDescribeTemplate() {

    init {
        describe("Call event") {
            val player = mock.addPlayer()
            val staff = mock.addPlayer()

            plugin.roleManager.setRole(staff, GameRole.STAFF)

            pluginManager.callEvent(GameStartEvent())

            it("Broadcast title") {
                player.nextTitle() shouldBe Config.START_GAME_STARTED_TITLE
            }

            it("Change Gamemodes") {
                player.gameMode shouldBe GameMode.SURVIVAL
                staff.gameMode shouldBe GameMode.CREATIVE
            }

            it("Change difficulty to HARD") {
                plugin.gameWorld.difficulty shouldBe Difficulty.HARD
            }
        }
    }

}
