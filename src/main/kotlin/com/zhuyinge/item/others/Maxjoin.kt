package com.zhuyinge.item.others

import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.player.PlayerLoginEvent
import taboolib.common.platform.event.SubscribeEvent


object Maxjoin {
    @SubscribeEvent
    @EventHandler(priority = EventPriority.HIGHEST)
    fun onLogin(event: PlayerLoginEvent) {
        if (event.result == PlayerLoginEvent.Result.KICK_FULL) {
            event.allow()
        }
    }
}