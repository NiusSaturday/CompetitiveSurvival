package me.lulu.competitivesurvival.commands

import io.kotest.matchers.shouldBe
import CommandTestTemplate
import me.lulu.competitivesurvival.Config
import org.bukkit.command.Command

class TestTogglePvPCommand : CommandTestTemplate() {

    override fun getTestCommand(): Command = plugin.togglePvPCommand

    init {
        assertCommandRegistered()

        describe("Executed without permission") {
            executeCommand()

            it("send no permission message") {
                sender.nextMessage() shouldBe Config.NO_PERMISSION
            }
        }

        describe("Executed with permission") {
            addPermission(Config.PERM_TOGGLE_PVP)

            val oldPvPState = plugin.pvpEnable
            executeCommand()

            it("PvP should be toggled") {
                plugin.pvpEnable shouldBe !oldPvPState
            }
        }
    }
}
