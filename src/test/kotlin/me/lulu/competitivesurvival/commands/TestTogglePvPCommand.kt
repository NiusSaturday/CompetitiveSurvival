package me.lulu.competitivesurvival.commands

import MockDescribeTemplate
import be.seeseemelk.mockbukkit.entity.PlayerMock
import io.kotest.matchers.shouldBe
import me.lulu.competitivesurvival.Config

class TestTogglePvPCommand : MockDescribeTemplate() {

    private lateinit var player: PlayerMock

    override fun beforeEachRoot() {
        player = mock.addPlayer()
    }

    init {
        describe("Executed without permission") {
            executeCommand()

            it("send no permission message") {
                player.nextMessage() shouldBe Config.NO_PERMISSION
            }
        }

        describe("Executed with permission") {
            player.addAttachment(plugin, Config.PERM_TOGGLE_PVP, true)

            val oldPvPState = plugin.pvpEnable
            executeCommand()

            it("PvP should be toggled") {
                plugin.pvpEnable shouldBe !oldPvPState
            }
        }
    }

    private fun executeCommand() {
        plugin.togglePvPCommand.execute(player, Config.CMD_TOGGLE_PVP, arrayOf())
    }
}
