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
            questionManager.checkAnsweringAnything(message, player)
        }
    else
        event<AsyncPlayerChatEvent> {
            questionManager.checkAnsweringAnything(message, player)
        }
}
