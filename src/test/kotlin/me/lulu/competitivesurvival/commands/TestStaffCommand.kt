package me.lulu.competitivesurvival.commands

import CommandTestTemplate
import io.kotest.matchers.shouldBe
import me.lulu.competitivesurvival.Config
import me.lulu.competitivesurvival.GameRole
import org.bukkit.GameMode

class TestStaffCommand : CommandTestTemplate() {
    override fun getTestCommand() = plugin.staffCommand

    init {
        assertCommandRegistered()

        describe("Execute without permission") {
            executeCommand()

            it("Blocked") {
                sender.nextMessage() shouldBe Config.NO_PERMISSION
            }
        }

        describe("Execute with permission") {

            beforeTest {
                sender = mock.addPlayer()

                sender.gameMode = GameMode.SURVIVAL
                addPermission(Config.PERM_STAFF)

                executeCommand()
            }

            it("Gamemode change to create") {
                sender.gameMode shouldBe GameMode.CREATIVE
            }

            it("Role change to STAFF") {
                plugin.roleManager.getRole(sender) shouldBe GameRole.STAFF
            }

            it("Send message to player") {
                sender.nextMessage() shouldBe Config.STAFF_MODE_ON
            }
        }
    }
}
