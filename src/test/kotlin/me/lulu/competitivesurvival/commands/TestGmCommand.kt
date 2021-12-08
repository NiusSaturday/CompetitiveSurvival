package me.lulu.competitivesurvival.commands

import CommandTestTemplate
import io.kotest.matchers.date.before
import io.kotest.matchers.shouldBe
import me.lulu.competitivesurvival.Config
import org.bukkit.GameMode

class TestGmCommand : CommandTestTemplate() {

    override fun getTestCommand() = plugin.gmCommand;

    init {
        assertCommandRegistered()

        describe("Player without permission") {
            executeCommand()

            it("Execute should be cancelled") {
                sender.nextMessage() shouldBe Config.NO_PERMISSION
            }
        }

        describe("Player with permission") {
            addPermission(Config.PERM_GM)

            describe("Execute with args") {

                beforeTest {
                    sender.gameMode = GameMode.SURVIVAL
                }

                it("Execute with no args, send help message") {
                    executeCommand()
                    sender.nextMessage() shouldBe Config.GM_HELP.toPlainText()
                }

                it("c args, makes player creative mode") {
                    executeCommand("c")
                    sender.gameMode shouldBe GameMode.CREATIVE
                }

                it("s args, makes player spectate mode") {
                    executeCommand("s")
                    sender.gameMode shouldBe GameMode.SPECTATOR
                }
            }
        }
    }
}
