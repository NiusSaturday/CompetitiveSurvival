package me.lulu.competitivesurvival

import br.com.devsrsouza.kotlinbukkitapi.extensions.item.item
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

data class Question(
    val title: String,
    val answer: String,
    val rewardMaterial: Material,
    val amount: Int,
    var picks: Int
) {
    fun reward(player: Player) {
        player.inventory.addItem(item(rewardMaterial, amount))
    }

    fun removePicks(i: Int) {
        this.picks -= i
    }
}
