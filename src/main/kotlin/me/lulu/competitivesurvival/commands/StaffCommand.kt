package me.lulu.competitivesurvival.commands

import br.com.devsrsouza.kotlinbukkitapi.dsl.command.command
import br.com.devsrsouza.kotlinbukkitapi.dsl.command.player
import br.com.devsrsouza.kotlinbukkitapi.extensions.text.msg
import me.lulu.competitivesurvival.CompetitiveSurvival
import me.lulu.competitivesurvival.Config
import me.lulu.competitivesurvival.GameRole
import org.bukkit.GameMode

fun CompetitiveSurvival.registerStaffCommand() = command(Config.CMD_STAFF) {
    permission = Config.PERM_STAFF
    permissionMessage = Config.NO_PERMISSION

    executorPlayer {
        player.gameMode = GameMode.CREATIVE
        roleManager.setRole(player, GameRole.STAFF)

        player.msg(Config.STAFF_MODE_ON)
    }
}
