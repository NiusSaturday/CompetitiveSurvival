package me.lulu.competitivesurvival.commands

import br.com.devsrsouza.kotlinbukkitapi.dsl.command.arguments.string
import br.com.devsrsouza.kotlinbukkitapi.dsl.command.command
import br.com.devsrsouza.kotlinbukkitapi.dsl.command.player
import br.com.devsrsouza.kotlinbukkitapi.extensions.text.msg
import me.lulu.competitivesurvival.CompetitiveSurvival
import me.lulu.competitivesurvival.Config
import org.bukkit.GameMode
import org.bukkit.entity.Player

fun CompetitiveSurvival.registerGmCommand() = command(Config.CMD_GM) {

    permission = Config.PERM_GM
    permissionMessage = Config.NO_PERMISSION
    usage = Config.GM_HELP.toLegacyText()

    executorPlayer {
        val mode = string(0, argMissing = Config.GM_HELP)

        when (mode) {
            "c" -> player.gm(GameMode.CREATIVE)
            "s" -> player.gm(GameMode.SPECTATOR)
            else -> player.msg(Config.GM_HELP)
        }
    }
}

private fun Player.gm(gameMode: GameMode) {
    this.gameMode = gameMode
}
