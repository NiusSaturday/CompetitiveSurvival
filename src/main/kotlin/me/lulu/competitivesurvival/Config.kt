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
    val PVP_TOGGLE_ON = +"&aPvP 已開啟"
    val PVP_TOGGLE_OFF = +"&cPvP 已關閉"

    val CMD_GM = "gm"
    val PERM_GM = "gm".asPerm()
    val GM_HELP = (+"&c使用方法: /gm <c/s>").asText()

    val CMD_STAFF = "staff"
    val PERM_STAFF = "staff".asPerm()
    val STAFF_MODE_ON = +"&a管理員模式已開啟"

    val CMD_QUESTION = "question"
    val PERM_QUESTION = "question".asPerm()
    val QUESTION_HELP = (+"&c使用方法: /question <標題> <正確答案> <獎勵物品> <數量> <前幾位有效>").asText()
    val QUESTION_INVLID_MATERIAL = (+"&c無效的物品名稱").asText()
    val QUESTION_INVLID_NUMBER = (+"&c物品的數量應為數字").asText()
    val QUESTION_TITLE_PREFIX = +"&c&l請問: &6&l"
    val QUESTION_SUB_TITLE = +"&7前 &f&l<picks> &7位玩家將獲得 &e&l<material> x<amount>"

    val NO_PERMISSION: String = +"&c你沒有權限執行此操作。"
    val FAIL_WAITING_STATE = (+"&c此指令在等待狀態還無法執行").asText()

    private fun String.asPerm(): String {
        return "niu.competitive-survival.$this"
    }
}
