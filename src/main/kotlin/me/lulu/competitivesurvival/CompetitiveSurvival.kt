package me.lulu.competitivesurvival

import br.com.devsrsouza.kotlinbukkitapi.architecture.KotlinPlugin
import br.com.devsrsouza.kotlinbukkitapi.dsl.command.CommandDSL
import me.lulu.competitivesurvival.commands.registerGmCommand
import me.lulu.competitivesurvival.commands.registerStaffCommand
import me.lulu.competitivesurvival.commands.registerTogglePvPCommand
import me.lulu.competitivesurvival.listener.registerDamageListener
import me.lulu.competitivesurvival.listener.registerJoinListener
import me.lulu.competitivesurvival.listener.registerRespawnListener
import me.lulu.competitivesurvival.manager.NoCleanManager
import me.lulu.competitivesurvival.manager.RoleManager
import org.bukkit.Difficulty
import org.bukkit.World
import org.bukkit.command.Command
import org.bukkit.plugin.PluginDescriptionFile
import org.bukkit.plugin.java.JavaPluginLoader
import java.io.File

class CompetitiveSurvival : KotlinPlugin {
    val roleManager = lifecycle { RoleManager(this) }
    val noCleanManager = lifecycle { NoCleanManager(this) }
    var pvpEnable: Boolean = false
    var gameState: GameState = GameState.WAITING

    lateinit var togglePvPCommand: CommandDSL
    lateinit var gmCommand: CommandDSL
    lateinit var staffCommand: CommandDSL

    override fun onPluginEnable() {
        togglePvPCommand = registerTogglePvPCommand()
        gmCommand = registerGmCommand()
        staffCommand = registerStaffCommand()

        registerDeathListener()
        registerRespawnListener()
        registerDamageListener()
        registerJoinListener()
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
