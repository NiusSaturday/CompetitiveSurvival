package me.lulu.competitivesurvival.manager

import MockDescribeTemplate
import io.kotest.matchers.shouldBe

class TestQuestionManager : MockDescribeTemplate() {

    private lateinit var manager: QuestionManager

    override fun beforeEachRoot() {
        super.beforeEachRoot()

        manager = plugin.questionManager
    }

    init {
        it("Default, question should be empty") {
            manager.getLatestQuestion() shouldBe null
        }
    }
}
