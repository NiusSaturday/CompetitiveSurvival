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
    val TOGGLE_PVP_FAIL_WAITING_STATE = +"&c此指令在等待狀態還無法執行"
    val PVP_TOGGLE_ON = +"&aPvP 已開啟"
    val PVP_TOGGLE_OFF = +"&cPvP 已關閉"

    val CMD_GM = "gm"
    val PERM_GM = "gm".asPerm()
    val GM_HELP = (+"&c使用方法: /gm <c/s>").asText()

    val CMD_STAFF = "staff"
    val PERM_STAFF = "staff".asPerm()
    val STAFF_MODE_ON = +"&a管理員模式已開啟"

    val CMD_QUESTION = "question"

    val NO_PERMISSION: String = +"&c你沒有權限執行此操作。"

    private fun String.asPerm(): String {
        return "niu.competitive-survival.$this"
    }
}
