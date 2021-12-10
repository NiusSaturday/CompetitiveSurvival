package me.lulu.competitivesurvival.manager

import br.com.devsrsouza.kotlinbukkitapi.architecture.lifecycle.LifecycleListener
import br.com.devsrsouza.kotlinbukkitapi.extensions.server.onlinePlayers
import me.lulu.competitivesurvival.CompetitiveSurvival
import me.lulu.competitivesurvival.Config
import me.lulu.competitivesurvival.Question
import org.bukkit.entity.Player

class QuestionManager(override val plugin: CompetitiveSurvival) : LifecycleListener<CompetitiveSurvival> {

    private val questions = mutableListOf<Question>()

    fun getLatestQuestion(): Question? {
        return questions.lastOrNull()
    }

    fun addQuestion(question: Question) {
        questions.add(question)
    }

    fun getQuestionForThisAnswer(message: String): Question? {
        return questions.firstOrNull {
            it.answer == message
        }
    }

    fun checkAnsweringAnything(message: String, player: Player) {
        getQuestionForThisAnswer(message)?.let {
            answerCorrect(it, player)
        }
    }

    private fun removeQuestion(question: Question) {
        this.questions.remove(question)
    }


    private fun answerCorrect(question: Question, player: Player) {
        if (question.isAnswered(player))
            return

        question.reward(player)
        question.addAnswered(player)

        if (question.isFullyAnswered()) {
            removeQuestion(question)

            onlinePlayers.forEach {
                it.sendTitle(
                    Config.QUESTION_FULLY_ANSWERED_TITLE,
                    Config.QUESTION_FULLY_ANSWERED_SUB.replace("<question>", question.title)
                )
            }
        }
    }
}
