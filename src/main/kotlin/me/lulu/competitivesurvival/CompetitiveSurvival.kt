package me.lulu.competitivesurvival

import br.com.devsrsouza.kotlinbukkitapi.architecture.KotlinPlugin
import org.bukkit.Difficulty
import org.bukkit.World
import org.bukkit.plugin.PluginDescriptionFile
import org.bukkit.plugin.java.JavaPluginLoader
import java.io.File

class CompetitiveSurvival : KotlinPlugin {
    constructor()

    constructor(loader: JavaPluginLoader?, description: PluginDescriptionFile?, dataFolder: File?, file: File?) : super(
        loader,
        description,
        dataFolder,
        file
    )

    override fun onPluginEnable() {
        registerDeathListener()
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
}
