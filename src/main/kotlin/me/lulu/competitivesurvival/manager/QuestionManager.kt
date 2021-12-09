package me.lulu.competitivesurvival.manager

import br.com.devsrsouza.kotlinbukkitapi.architecture.lifecycle.LifecycleListener
import me.lulu.competitivesurvival.CompetitiveSurvival
import me.lulu.competitivesurvival.Question

class QuestionManager(override val plugin: CompetitiveSurvival) : LifecycleListener<CompetitiveSurvival> {

    private val questions = mutableListOf<Question>()

    fun getLatestQuestion(): Question? {
        return questions.lastOrNull()
    }

    fun addQuestion(question: Question) {
        questions.add(question)
    }

}
