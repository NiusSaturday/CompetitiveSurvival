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
                manager.getLatestQuestion()!!.picks shouldBe 3
                player.inventory.isEmpty shouldBe true
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

            it("picks minus 1") {
                manager.getLatestQuestion()!!.picks shouldBe 2
            }

            describe("Answer again") {
                val inventoryPrev = player.inventory

                player.chat("2")

                it("Do nothing") {
                    manager.getLatestQuestion()!!.picks shouldBe 2
                    player.inventory shouldBe inventoryPrev
                }
            }
        }
    }
}
