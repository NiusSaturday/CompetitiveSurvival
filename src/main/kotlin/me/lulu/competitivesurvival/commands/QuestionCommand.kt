package me.lulu.competitivesurvival.commands

import br.com.devsrsouza.kotlinbukkitapi.dsl.command.arguments.int
import br.com.devsrsouza.kotlinbukkitapi.dsl.command.arguments.material
import br.com.devsrsouza.kotlinbukkitapi.dsl.command.arguments.string
import br.com.devsrsouza.kotlinbukkitapi.dsl.command.command
import br.com.devsrsouza.kotlinbukkitapi.dsl.command.fail
import br.com.devsrsouza.kotlinbukkitapi.extensions.server.onlinePlayers
import me.lulu.competitivesurvival.CompetitiveSurvival
import me.lulu.competitivesurvival.Config
import me.lulu.competitivesurvival.GameState
import me.lulu.competitivesurvival.Question

fun CompetitiveSurvival.registerQuestionCommand() = command(Config.CMD_QUESTION) {
    permission = Config.PERM_QUESTION
    permissionMessage = Config.NO_PERMISSION

    val helpMessage = Config.QUESTION_HELP

    executorPlayer {

        if (gameState == GameState.WAITING)
            fail(Config.FAIL_WAITING_STATE)

        val title = string(0, argMissing = helpMessage)
        val answer = string(1, argMissing = helpMessage)
        val material = material(2, argMissing = helpMessage, notFound = Config.QUESTION_INVLID_MATERIAL)
        val amount = int(3, argMissing = helpMessage, numberFormat = Config.QUESTION_INVLID_NUMBER)
        val picks = int(4, argMissing = helpMessage, numberFormat = Config.QUESTION_INVLID_NUMBER)

        if (questionManager.getQuestionForThisAnswer(answer) != null)
            fail(Config.ANSWER_SHOULD_BE_SINGLETON)

        questionManager.addQuestion(
            Question(
                title = title,
                answer = answer,
                rewardMaterial = material,
                amount = amount,
                picks = picks
            )
        )

        onlinePlayers.forEach {
            it.sendTitle(
                "${Config.QUESTION_TITLE_PREFIX}${title}",
                Config.QUESTION_SUB_TITLE
                    .replace("<picks>", "$picks")
                    .replace("<material>", material.name)
                    .replace("<amount>", "$amount")
            )
        }
    }
}
