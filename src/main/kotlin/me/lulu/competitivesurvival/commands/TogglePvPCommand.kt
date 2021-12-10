package me.lulu.competitivesurvival.commands

import br.com.devsrsouza.kotlinbukkitapi.dsl.command.command
import br.com.devsrsouza.kotlinbukkitapi.extensions.server.onlinePlayers
import br.com.devsrsouza.kotlinbukkitapi.extensions.text.msg
import me.lulu.competitivesurvival.CompetitiveSurvival
import me.lulu.competitivesurvival.Config
import me.lulu.competitivesurvival.GameState

fun CompetitiveSurvival.registerTogglePvPCommand() = command(Config.CMD_TOGGLE_PVP) {

    permission = Config.PERM_TOGGLE_PVP
    permissionMessage = Config.NO_PERMISSION

    executorPlayer {
        if (gameState == GameState.WAITING) {
            sender.msg(Config.FAIL_WAITING_STATE.toPlainText())
            return@executorPlayer
        }

        pvpEnable = !pvpEnable

        onlinePlayers.forEach { it.sendTitle(Config.PVP_TOGGLE_ON, "") }
    }
}
