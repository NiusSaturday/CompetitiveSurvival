package me.lulu.competitivesurvival.manager

import br.com.devsrsouza.kotlinbukkitapi.architecture.lifecycle.LifecycleListener
import me.lulu.competitivesurvival.CompetitiveSurvival
import me.lulu.competitivesurvival.Config
import org.bukkit.entity.Player

class NoCleanManager(override val plugin: CompetitiveSurvival) : LifecycleListener<CompetitiveSurvival> {

    fun getNoCleanEnds(player: Player): Long {
        return System.currentTimeMillis() + (Config.NO_CLEAN_SECONDS * 1000)
    }

}
