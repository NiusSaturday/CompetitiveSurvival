package me.lulu.competitivesurvival

import br.com.devsrsouza.kotlinbukkitapi.extensions.text.asText
import br.com.devsrsouza.kotlinbukkitapi.extensions.text.unaryPlus

object Config {
    const val WORLD_NAME = "world"
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
    val RECEIVED_REWARD = +"&a&l恭喜你答對！獎品已發到背包當中"
    val PLAYER_CORRECT_BROADCAST = +"&6&l<player> 答對了！"
    val ANSWER_SHOULD_BE_SINGLETON = (+"&c這個答案已經被其他問題使用了").asText()
    val QUESTION_FULLY_ANSWERED_TITLE = +"&6&l問題已經被所有人回答完畢"
    val QUESTION_FULLY_ANSWERED_SUB = +"&7內容: <question>"

    val CMD_START = "start"
    val PERM_START = "start".asPerm()
    val START_ALREADY_STARTED = (+"&c遊戲已經開始了").asText()
    val START_GAME_STARTED_TITLE = +"&6&l遊戲開始了！"


    val WIN_NO_ONE_WINS = +"&c&l終界龍因為其他原因死亡，因此沒人獲得勝利。"
    val WIN_WINNER_TITLE = +"&6&l終界龍被 <killer> 殺死了"
    val WIN_WINNER_SUB = +"&7&l恭喜玩家獲得本次星期妞的大勝利！"

    val SPECING_PLAYER_INV = +"&7正在觀察 <player> 的背包..."

    val NO_PERMISSION: String = +"&c你沒有權限執行此操作。"
    val FAIL_WAITING_STATE = (+"&c此指令在等待狀態還無法執行").asText()

    private fun String.asPerm(): String {
        return "niu.competitive-survival.$this"
    }
}
