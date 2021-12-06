package me.lulu.competitivesurvival.manager

import br.com.devsrsouza.kotlinbukkitapi.architecture.lifecycle.LifecycleListener
import com.sun.javafx.scene.traversal.SubSceneTraversalEngine
import me.lulu.competitivesurvival.CompetitiveSurvival
import me.lulu.competitivesurvival.Config
import org.bukkit.entity.Player
import java.util.*

class NoCleanManager(override val plugin: CompetitiveSurvival) : LifecycleListener<CompetitiveSurvival> {

    private val noCleanMap = mutableMapOf<UUID, Long>()

    fun getNoCleanEnds(player: Player): Long {
        return noCleanMap.getOrDefault(player.uniqueId, System.currentTimeMillis())
    }

    fun setNoCleanSeconds(player: Player, noCleanSeconds: Int) {
        noCleanMap[player.uniqueId] = System.currentTimeMillis() + (noCleanSeconds * 1000)
    }

    fun isNoCleaning(player: Player): Boolean {
        return getNoCleanEnds(player) > System.currentTimeMillis()
    }

}
