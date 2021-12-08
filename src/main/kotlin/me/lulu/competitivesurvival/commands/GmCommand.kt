package me.lulu.competitivesurvival.commands

import br.com.devsrsouza.kotlinbukkitapi.dsl.command.command
import me.lulu.competitivesurvival.CompetitiveSurvival
import me.lulu.competitivesurvival.Config

fun CompetitiveSurvival.registerGmCommand() = command(Config.CMD_GM) {

    permission = Config.PERM_GM
    permissionMessage = Config.NO_PERMISSION

    executorPlayer {

    }
}
