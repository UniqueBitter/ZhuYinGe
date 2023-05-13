package com.zhuyinge.item.others

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import taboolib.common.platform.event.SubscribeEvent

object Manren {
    private val onlinePlayers = Bukkit.getOnlinePlayers().size
    private val maxPlayer = Bukkit.getMaxPlayers()
    private val whitelist = mutableListOf(
        "Lanterns_beiren",
        "Unique_Bitter",
        "xiaoxiaoOWO",
        "lonely0719"
    )

    @SubscribeEvent
    fun onPlayerJoin(event: PlayerJoinEvent) {
        val player = event.player
        val isOp = player.isOp
        if (onlinePlayers >= maxPlayer - 3) {
            event.joinMessage = null
        }
        if (whitelist.contains(player.name) || isOp) {
            return
        }
        if (onlinePlayers >= maxPlayer - 3) {
            player.kickPlayer("${ChatColor.RED}你没有剩余空位的资格，抱歉")
        }

    }

    @SubscribeEvent
    fun PlayerQuit(event: PlayerQuitEvent) {
        val player = event.player
        if (onlinePlayers <= maxPlayer - 3) {
            return
        }
        event.quitMessage = null
    }
}