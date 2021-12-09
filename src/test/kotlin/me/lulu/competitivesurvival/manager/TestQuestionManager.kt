package me.lulu.competitivesurvival.manager

import MockBukkitTemplate
import io.kotest.matchers.shouldBe

class TestQuestionManager : MockBukkitTemplate() {

    private lateinit var manager: QuestionManager

    @BeforeEach
    override fun setup() {
        super.setup()

        manager = plugin.questionManager
    }

    @Test
    fun Default_QuestionDoesNotExist() {
        manager.getLatestQuestion() shouldBe null
    }
}
