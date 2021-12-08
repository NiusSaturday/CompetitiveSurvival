package me.lulu.competitivesurvival

import br.com.devsrsouza.kotlinbukkitapi.extensions.text.asText
import br.com.devsrsouza.kotlinbukkitapi.extensions.text.unaryPlus

object Config {
    const val WORLD_NAME = "game_world"
    const val RESPAWN_HEALTH = 20.0
    const val RESPAWN_Y = 200.0
    const val NO_CLEAN_SECONDS = 10

    val CMD_TOGGLE_PVP = "toggle-pvp"
    val PERM_TOGGLE_PVP = "toggle-pvp".asPerm()

    val CMD_GM = "gm"
    val PERM_GM = "gm".asPerm()
    val GM_HELP = (+"&c使用方法: /gm <c/s>").asText()

    val NO_PERMISSION: String = +"&c你沒有權限執行此操作。"

    private fun String.asPerm(): String {
        return "niu.competitive-survival.$this"
    }
}
