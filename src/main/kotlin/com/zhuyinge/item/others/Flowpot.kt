package com.zhuyinge.item.others

import org.bukkit.event.player.PlayerInteractEvent
import taboolib.common.platform.event.SubscribeEvent

object Flowpot {
    @SubscribeEvent
    fun onInt(event: PlayerInteractEvent) {
        val player = event.player
        val block = event.clickedBlock ?: return
        if (block.type == org.bukkit.Material.FLOWER_POT) {
            if (player.isOp){
                return
            }
            event.isCancelled = true
        }

    }
}
