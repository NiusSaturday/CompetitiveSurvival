package me.lulu.competitivesurvival

import br.com.devsrsouza.kotlinbukkitapi.architecture.KotlinPlugin
import br.com.devsrsouza.kotlinbukkitapi.dsl.command.CommandDSL
import me.lulu.competitivesurvival.commands.registerGmCommand
import me.lulu.competitivesurvival.commands.registerQuestionCommand
import me.lulu.competitivesurvival.commands.registerStaffCommand
import me.lulu.competitivesurvival.commands.registerTogglePvPCommand
import me.lulu.competitivesurvival.listener.registerDamageListener
import me.lulu.competitivesurvival.listener.registerJoinListener
import me.lulu.competitivesurvival.listener.registerQuestionListener
import me.lulu.competitivesurvival.listener.registerRespawnListener
import me.lulu.competitivesurvival.manager.NoCleanManager
import me.lulu.competitivesurvival.manager.QuestionManager
import me.lulu.competitivesurvival.manager.RoleManager
import org.bukkit.Bukkit
import org.bukkit.Difficulty
import org.bukkit.World
import org.bukkit.WorldCreator
import org.bukkit.command.Command
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

    /** todo
     *   bukkit.createWorld() 要先能做基礎 mock，不然很多跟世界有關的東西很難測試
     */

    override fun onPluginEnable() {
        togglePvPCommand = registerTogglePvPCommand()
        gmCommand = registerGmCommand()
        staffCommand = registerStaffCommand()
        questionCommand = registerQuestionCommand()

        registerDeathListener()
        registerRespawnListener()
        registerDamageListener()
        registerJoinListener()
        registerQuestionListener()

        this.gameWorld = Bukkit.createWorld(WorldCreator(Config.WORLD_NAME))
        setupWorld(this.gameWorld)
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

    companion object {
        var unitTesting = false
    }
}
