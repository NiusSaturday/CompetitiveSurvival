package me.lulu.competitivesurvival.listener

import br.com.devsrsouza.kotlinbukkitapi.extensions.event.event
import br.com.devsrsouza.kotlinbukkitapi.extensions.event.events
import me.lulu.competitivesurvival.CompetitiveSurvival
import me.lulu.competitivesurvival.GameState
import org.bukkit.event.entity.FoodLevelChangeEvent

fun CompetitiveSurvival.registerMiscListener() = events {

    event<FoodLevelChangeEvent> {
        if (gameState == GameState.WAITING)
            foodLevel = 20
    }

}
