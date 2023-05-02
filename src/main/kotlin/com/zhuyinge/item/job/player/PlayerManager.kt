package com.zhuyinge.item.job.player

import org.bukkit.entity.Player
import top.maplex.panlingitem.api.PanLingAPI
import top.maplex.panlingitem.api.PanLingStatic
import java.util.*
import java.util.concurrent.ConcurrentHashMap

object PlayerManager {

    val players = ConcurrentHashMap<UUID, PlayerData>()

    fun getJob(player: Player): JobType {
        return when (PanLingAPI.getPlayerData(player, PanLingStatic.JOB)) {
            "0" -> JobType.ZHANSHI
            "1" -> JobType.LIANDAN
            "2" -> JobType.GONGJIAN
            else -> JobType.NONE
        }
    }
}