package top.maplex.panlingitem.api

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import taboolib.common.platform.command.player

object PanLingAPI {

    private val manager by lazy {
        Bukkit.getScoreboardManager()
    }

    fun getPlayerData(player: Player, key: PanLingStatic): String {
        return manager?.mainScoreboard?.getObjective(key.id)?.let {
            val data = it.getScore(player.name)
            data.score.toString()
        } ?: "0"
    }

}