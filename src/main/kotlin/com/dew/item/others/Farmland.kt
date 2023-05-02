package com.dew.item.others

import org.bukkit.Material
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
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
            }
        }
    }