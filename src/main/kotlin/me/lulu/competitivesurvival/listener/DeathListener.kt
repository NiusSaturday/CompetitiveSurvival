package me.lulu.competitivesurvival

import br.com.devsrsouza.kotlinbukkitapi.extensions.event.event
import br.com.devsrsouza.kotlinbukkitapi.extensions.event.events
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.Player
import org.bukkit.event.entity.PlayerDeathEvent
import kotlin.random.Random

fun CompetitiveSurvival.registerDeathListener() = events {

    event<PlayerDeathEvent> {
        val player = entity

        keepInventory = false
    }

}
