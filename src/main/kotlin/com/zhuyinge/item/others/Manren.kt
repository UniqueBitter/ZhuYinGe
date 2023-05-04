package com.zhuyinge.item.others

import org.bukkit.Bukkit
import org.bukkit.event.player.PlayerJoinEvent
import taboolib.common.platform.event.SubscribeEvent

object Manren {
    @SubscribeEvent
    fun max(event: PlayerJoinEvent) {
        val onlinePlayers = Bukkit.getOnlinePlayers().size
        val player = event.player
        val isOp = player.isOp
        val maxPlayers = 32
        //Bukkit.getMaxPlayers()
        if (onlinePlayers >= maxPlayers && !isOp) {
            player.kickPlayer(error("服务器已满员！"))
        }
    }
}