package me.lulu.competitivesurvival

import br.com.devsrsouza.kotlinbukkitapi.architecture.KotlinPlugin
import br.com.devsrsouza.kotlinbukkitapi.dsl.command.CommandDSL
import me.lulu.competitivesurvival.commands.*
import me.lulu.competitivesurvival.listener.*
import me.lulu.competitivesurvival.manager.NoCleanManager
import me.lulu.competitivesurvival.manager.QuestionManager
import me.lulu.competitivesurvival.manager.RoleManager
import org.bukkit.Bukkit
import org.bukkit.Difficulty
import org.bukkit.World
import org.bukkit.WorldCreator
import org.bukkit.plugin.PluginDescriptionFile
import org.bukkit.plugin.java.JavaPluginLoader
import java.io.File

class CompetitiveSurvival : KotlinPlugin {
    val questionManager = lifecycle { QuestionManager(this) }
    val roleManager = lifecycle { RoleManager(this) }
    val noCleanManager = lifecycle { NoCleanManager(this) }

    var pvpEnable: Boolean = false
    var gameState: GameState = GameState.WAITING
    lateinit var gameWorld: World

    lateinit var togglePvPCommand: CommandDSL
    lateinit var gmCommand: CommandDSL
    lateinit var staffCommand: CommandDSL
    lateinit var questionCommand: CommandDSL
    lateinit var startCommand: CommandDSL

    override fun onPluginEnable() {
        togglePvPCommand = registerTogglePvPCommand()
        gmCommand = registerGmCommand()
        staffCommand = registerStaffCommand()
        questionCommand = registerQuestionCommand()
        startCommand = registerStartCommand()

        registerDeathListener()
        registerRespawnListener()
        registerDamageListener()
        registerJoinListener()
        registerQuestionListener()
        registerGameStartListener()
        registerMiscListener()
        registerWinListener()
        registerInvSeeListener()

        this.gameWorld = Bukkit.createWorld(WorldCreator(Config.WORLD_NAME))
        setupWorld(this.gameWorld)
    }

    fun setupWorld(world: World) {
        world.difficulty = Difficulty.PEACEFUL
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

    companion object {
        var unitTesting = false
    }
}
