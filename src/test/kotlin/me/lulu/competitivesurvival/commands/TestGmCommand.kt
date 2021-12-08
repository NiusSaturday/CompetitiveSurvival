package me.lulu.competitivesurvival.commands

import CommandTestTemplate
import io.kotest.matchers.shouldBe
import me.lulu.competitivesurvival.Config

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
    }
}
