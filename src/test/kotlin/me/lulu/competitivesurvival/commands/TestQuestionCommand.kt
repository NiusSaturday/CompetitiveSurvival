package me.lulu.competitivesurvival.commands

import CommandTestTemplate

class TestQuestionCommand : CommandTestTemplate() {
    override fun getTestCommand() = plugin.questionCommand

    init {
        assertCommandRegistered()
    }
}
