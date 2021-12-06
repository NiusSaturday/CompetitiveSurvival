package me.lulu.competitivesurvival

import br.com.devsrsouza.kotlinbukkitapi.architecture.KotlinPlugin
import me.lulu.competitivesurvival.listener.registerDamageListener
import me.lulu.competitivesurvival.manager.NoCleanManager
import org.bukkit.Difficulty
import org.bukkit.World
import org.bukkit.plugin.PluginDescriptionFile
import org.bukkit.plugin.java.JavaPluginLoader
import java.io.File

class CompetitiveSurvival : KotlinPlugin {
    val noCleanManager = lifecycle { NoCleanManager(this) }
    var pvpEnable: Boolean = false

    override fun onPluginEnable() {
        registerDeathListener()
        registerDamageListener()
    }

    fun setupWorld(world: World) {
        world.difficulty = Difficulty.HARD
        setupWorldBorder(world)
    }

    private fun setupWorldBorder(world: World) {
        val border = world.worldBorder
        border.setCenter(0.0, 0.0)
        border.size = 16.0
    }


    constructor()

    constructor(loader: JavaPluginLoader?, description: PluginDescriptionFile?, dataFolder: File?, file: File?) : super(
        loader,
        description,
        dataFolder,
        file
    )
}
