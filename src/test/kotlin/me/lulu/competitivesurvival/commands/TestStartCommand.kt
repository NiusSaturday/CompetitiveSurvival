package me.lulu.competitivesurvival.commands

import CommandTestTemplate
import io.kotest.matchers.shouldBe
import me.lulu.competitivesurvival.Config
import me.lulu.competitivesurvival.GameState
import me.lulu.competitivesurvival.events.GameStartEvent

class TestStartCommand : CommandTestTemplate() {
    override fun getTestCommand() = plugin.startCommand

    init {
        assertCommandRegistered()

        describe("Executed with No permission") {
            executeCommand()

            it("Blocked, send no permission message") {
                sender.nextMessage() shouldBe Config.NO_PERMISSION
            }
        }

        describe("Executed with permission") {
            addPermission(Config.PERM_START)

            describe("Gaming State") {
                plugin.gameState = GameState.GAMING

                it("Execute, send already started") {
                    executeCommand()

                    sender.nextMessage() shouldBe Config.START_ALREADY_STARTED.toPlainText()
                }
            }

            describe("Waiting State") {
                plugin.gameState = GameState.WAITING

                it("Execute, start the game!") {
                    executeCommand()

                    plugin.gameState shouldBe GameState.GAMING
                    mock.pluginManager.assertEventFired(GameStartEvent::class.java)
                }
            }
        }
    }
}
