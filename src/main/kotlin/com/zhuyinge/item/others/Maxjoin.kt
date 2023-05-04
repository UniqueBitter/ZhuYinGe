package com.zhuyinge.item.others

import org.bukkit.event.player.PlayerLoginEvent
import taboolib.common.platform.event.EventPriority
import taboolib.common.platform.event.SubscribeEvent


object Maxjoin {
    private val whitelist = mutableListOf(
        "Lanterns_beiren",
        "Unique_Bitter",
        "xiaoxiaoOWO",
        "lonely0719"
    )

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    fun onLogin(event: PlayerLoginEvent) {
        if (event.result == PlayerLoginEvent.Result.KICK_FULL) {
            val player = event.player
            val isOp = player.isOp
            if (isOp || whitelist.contains(player.name)) {
                event.allow()
            }
        }
    }
}