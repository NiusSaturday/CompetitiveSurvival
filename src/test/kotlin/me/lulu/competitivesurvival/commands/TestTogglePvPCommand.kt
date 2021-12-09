package me.lulu.competitivesurvival.commands

import io.kotest.matchers.shouldBe
import CommandTestTemplate
import be.seeseemelk.mockbukkit.entity.PlayerMock
import br.com.devsrsouza.kotlinbukkitapi.extensions.server.player
import me.lulu.competitivesurvival.Config
import me.lulu.competitivesurvival.GameState
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


            describe("Waiting State") {
                plugin.gameState = GameState.WAITING

                it("Can not executed") {
                    executeCommandCheckToggled() shouldBe false
                    sender.nextMessage() shouldBe Config.TOGGLE_PVP_FAIL_WAITING_STATE
                }
            }

            describe("Execute Gaming State") {
                plugin.gameState = GameState.GAMING
                val toggled = executeCommandCheckToggled()

                it("PvP should be toggled") {
                    toggled shouldBe true
                }

                describe("Also send broadcast") {
                    it("Everyone gets toggled broadcast") {
                        sender.nextTitle() shouldBe Config.PVP_TOGGLE_ON
                    }
                }
            }
        }
    }

    private fun executeCommandCheckToggled(): Boolean {
        val oldPvPState = plugin.pvpEnable

        executeCommand()

        return plugin.pvpEnable != oldPvPState
    }
}
