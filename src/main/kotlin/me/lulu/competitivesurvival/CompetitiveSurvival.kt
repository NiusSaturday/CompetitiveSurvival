package me.lulu.competitivesurvival

import org.bukkit.plugin.PluginDescriptionFile
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.plugin.java.JavaPluginLoader
import java.io.File

class CompetitiveSurvival : JavaPlugin {
    constructor()

    constructor(
        loader: JavaPluginLoader?,
        description: PluginDescriptionFile?,
        dataFolder: File?,
        file: File?
    ) : super(
        loader,
        description,
        dataFolder,
        file
    )
}
