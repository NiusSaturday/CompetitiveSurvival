package me.lulu.competitivesurvival.commands

import br.com.devsrsouza.kotlinbukkitapi.dsl.command.arguments.string
import br.com.devsrsouza.kotlinbukkitapi.dsl.command.command
import br.com.devsrsouza.kotlinbukkitapi.dsl.command.player
import me.lulu.competitivesurvival.CompetitiveSurvival
import me.lulu.competitivesurvival.Config
import org.bukkit.GameMode

fun CompetitiveSurvival.registerGmCommand() = command(Config.CMD_GM) {

    permission = Config.PERM_GM
    permissionMessage = Config.NO_PERMISSION
    usage = Config.GM_HELP.toLegacyText()

    executorPlayer {
        val mode = string(0, argMissing = Config.GM_HELP)

        when (mode) {
            "c" -> player.gameMode = GameMode.CREATIVE
            "s" -> player.gameMode = GameMode.SPECTATOR
        }
    }
}
