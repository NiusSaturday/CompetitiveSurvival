package me.lulu.competitivesurvival

import br.com.devsrsouza.kotlinbukkitapi.extensions.bukkit.broadcast
import br.com.devsrsouza.kotlinbukkitapi.extensions.item.item
import br.com.devsrsouza.kotlinbukkitapi.extensions.player.playSound
import br.com.devsrsouza.kotlinbukkitapi.extensions.text.msg
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.util.*

data class Question(
    val title: String,
    val answer: String,
    val rewardMaterial: Material,
    val amount: Int,
    val picks: Int
) {
    private val answered = mutableListOf<UUID>()

    fun answerCorrect(player: Player) {
        reward(player)
        answered.add(player.uniqueId)
    }

    private fun reward(player: Player) {
        player.inventory.addItem(item(rewardMaterial, amount))

        player.sendTitle(Config.RECEIVED_REWARD, "")
        broadcast(Config.PLAYER_CORRECT_BROADCAST.replace("<player>", player.name))
    }

    fun isAnswered(player: Player): Boolean {
        return this.answered.contains(player.uniqueId)
    }

    fun getAnsweredPlayers() = this.answered.toList()

    fun isFullyAnswered(): Boolean {
        return this.getAnsweredPlayers().size >= this.picks
    }
}
