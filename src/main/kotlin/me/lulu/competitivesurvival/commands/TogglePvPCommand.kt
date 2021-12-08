package me.lulu.competitivesurvival.commands

import br.com.devsrsouza.kotlinbukkitapi.dsl.command.command
import me.lulu.competitivesurvival.CompetitiveSurvival
import me.lulu.competitivesurvival.Config

fun CompetitiveSurvival.registerTogglePvPCommand() = command(Config.CMD_TOGGLE_PVP) {

    permission = Config.PERM_TOGGLE_PVP
    permissionMessage = Config.NO_PERMISSION

    executorPlayer {
        pvpEnable = !pvpEnable
    }
}
