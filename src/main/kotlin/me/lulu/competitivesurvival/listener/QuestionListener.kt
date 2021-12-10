package me.lulu.competitivesurvival.listener

import br.com.devsrsouza.kotlinbukkitapi.extensions.event.event
import br.com.devsrsouza.kotlinbukkitapi.extensions.event.events
import me.lulu.competitivesurvival.CompetitiveSurvival
import org.bukkit.entity.Player
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.event.player.PlayerChatEvent

fun CompetitiveSurvival.registerQuestionListener() = events {

    if (CompetitiveSurvival.unitTesting)
        event<PlayerChatEvent> {
            check(message, player)
        }
    else
        event<AsyncPlayerChatEvent> {
            check(message, player)
        }
}

private fun CompetitiveSurvival.check(msg: String, player: Player) {
    questionManager.getQuestionForThisAnswer(msg)?.let {
        if (it.isAnswered(player))
            return
        else
            it.answerCorrect(player)
    }
}
