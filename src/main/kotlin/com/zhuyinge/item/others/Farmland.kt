package com.zhuyinge.item.others

import com.zhuyinge.item.utils.error
import com.zhuyinge.item.utils.forAll
import org.bukkit.event.player.PlayerInteractEvent
import taboolib.common.platform.event.SubscribeEvent

object Farmland {
    @SubscribeEvent
    fun onInt(event: PlayerInteractEvent) {
        val player = event.player
        val block = event.clickedBlock ?: return
        if (block.type == org.bukkit.Material.FARMLAND) {
            if (player.isOp) {
                return
            }
            event.isCancelled = true
            player.error("不要破坏粮食哦=-=")
            player.addScoreboardTag("fanren")
            ("${player.name}意图踩踏农田，人皇震怒。被打入水牢").forAll()
        }
    }
}