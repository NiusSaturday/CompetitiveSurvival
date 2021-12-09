package me.lulu.competitivesurvival.commands

import CommandTestTemplate
import io.kotest.matchers.shouldBe
import me.lulu.competitivesurvival.Config
import me.lulu.competitivesurvival.Question
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.Material

class TestQuestionCommand : CommandTestTemplate() {
    override fun getTestCommand() = plugin.questionCommand

    init {
        assertCommandRegistered()

        describe("Without permission") {
            executeCommand()

            it("Block with no permission message") {
                sender.nextMessage() shouldBe Config.NO_PERMISSION
            }
        }

        describe("With permission") {
            beforeTest {
                sender = mock.addPlayer()
                addPermission(Config.PERM_QUESTION)
            }

            it("1, 2 args, fail with usage message") {
                assertInvalid("question", Config.QUESTION_HELP)
                assertInvalid("question answer", Config.QUESTION_HELP)
            }

            describe("3 args") {
                it("Invalid material, fail with invalid material message") {
                    assertInvalid("question answer THIS_MATERIAL_IS_INVALID", Config.QUESTION_INVLID_MATERIAL)
                }

                it("Valid material, still failed with help message") {
                    assertInvalid("question answer STONE", Config.QUESTION_HELP)
                }
            }

            describe("4 args") {
                it("Not Number, return invalid number message") {
                    assertInvalid("question answer STONE IM_NOT_NUMBER", Config.QUESTION_INVLID_NUMBER)
                }

                it("Valid number, still failed with help message") {
                    assertInvalid("question answer STONE 1", Config.QUESTION_HELP)
                }
            }

            describe("5 args") {
                it("Not Number, return invalid number message") {
                    assertInvalid("question answer STONE 1 NOT_NUMBER", Config.QUESTION_INVLID_NUMBER)
                }

                describe("Valid number") {
                    execCmdStr("question answer STONE 1 1")

                    it("Check question is added to question manager") {
                        val questionManager = plugin.questionManager
                        val question: Question = questionManager.getLatestQuestion()!!

                        question.title shouldBe "question"
                        question.answer shouldBe "answer"
                        question.rewardMaterial shouldBe Material.STONE
                        question.amount shouldBe 1
                        question.picks shouldBe 1
                    }
                }
            }
        }
    }

    private fun assertInvalid(args: String, exceptedMessage: TextComponent) {
        execCmdStr(args)
        sender.nextMessage() shouldBe exceptedMessage.toPlainText()
    }

    private fun execCmdStr(argsInString: String) {
        executeCommand(args = argsInString.split(" ").toTypedArray())
    }
}
