package me.lulu.competitivesurvival.listener

import MockDescribeTemplate
import br.com.devsrsouza.kotlinbukkitapi.extensions.item.item
import io.kotest.matchers.shouldBe
import me.lulu.competitivesurvival.Config
import me.lulu.competitivesurvival.Question
import me.lulu.competitivesurvival.manager.QuestionManager
import org.bukkit.Material

class TestQuestionListener : MockDescribeTemplate() {

    private lateinit var manager: QuestionManager

    override fun beforeEachRoot() {
        super.beforeEachRoot()

        manager = plugin.questionManager
        manager.addQuestion(Question("1+1", "2", Material.STONE, 64, 3))
    }

    init {

        describe("Answer incorrect") {
            val player = mock.addPlayer()
            player.chat("WRONG_ANSWER")

            it("Nothing happens") {
                manager.getLatestQuestion()!!.isAnswered(player) shouldBe false
            }
        }

        describe("Answer correct") {
            val player = mock.addPlayer()
            player.chat("2")

            it("give reward") {
                val item = player.inventory.first()

                item.type shouldBe Material.STONE
                item.amount shouldBe 64
                player.nextTitle() shouldBe Config.RECEIVED_REWARD
                player.nextMessage() shouldBe Config.PLAYER_CORRECT_BROADCAST
                    .replace("<player>", player.name)
            }

            it("mark answered") {
                manager.getLatestQuestion()!!.isAnswered(player) shouldBe true
            }
        }

        describe("Answer twice") {
            val player = mock.addPlayer()

            player.chat("2")
            player.chat("2")

            it("Only reward once") {
                manager.getLatestQuestion()!!.getAnsweredPlayers().size shouldBe 1
            }
        }

        describe("Fully answered") {
            val question = manager.getLatestQuestion()!!
            val players = listOf(mock.addPlayer(), mock.addPlayer(), mock.addPlayer())

            players.forEach { it.chat("2"); it.nextTitle();it.nextSubTitle() }

            it("Remove this question") {
                manager.getQuestionForThisAnswer("2") shouldBe null
            }

            it("Broadcast question is fully answered") {
                players.forEach {
                    it.nextTitle() shouldBe Config.QUESTION_FULLY_ANSWERED_TITLE
                    it.nextSubTitle() shouldBe Config.QUESTION_FULLY_ANSWERED_SUB.replace("<question>", question.title)
                }
            }
        }
    }
}
