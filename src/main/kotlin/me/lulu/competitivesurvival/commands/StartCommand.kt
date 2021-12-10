package me.lulu.competitivesurvival.commands

import br.com.devsrsouza.kotlinbukkitapi.dsl.command.command
import br.com.devsrsouza.kotlinbukkitapi.dsl.command.fail
import br.com.devsrsouza.kotlinbukkitapi.dsl.command.player
import br.com.devsrsouza.kotlinbukkitapi.extensions.server.onlinePlayers
import br.com.devsrsouza.kotlinbukkitapi.extensions.server.pluginManager
import br.com.devsrsouza.kotlinbukkitapi.extensions.text.msg
import me.lulu.competitivesurvival.CompetitiveSurvival
import me.lulu.competitivesurvival.Config
import me.lulu.competitivesurvival.GameState
import me.lulu.competitivesurvival.events.GameStartEvent

fun CompetitiveSurvival.registerStartCommand() = command(Config.CMD_START) {
    permission = Config.PERM_START
    permissionMessage = Config.NO_PERMISSION

    executorPlayer {
        if (gameState == GameState.GAMING)
            fail(Config.START_ALREADY_STARTED)

        gameState = GameState.GAMING
        pluginManager.callEvent(GameStartEvent())
    }

}
