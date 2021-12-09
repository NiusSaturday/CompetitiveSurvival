package me.lulu.competitivesurvival

import org.bukkit.Material

data class Question(
    val title: String,
    val answer: String,
    val rewardMaterial: Material,
    val amount: Int,
    val picks: Int
)
