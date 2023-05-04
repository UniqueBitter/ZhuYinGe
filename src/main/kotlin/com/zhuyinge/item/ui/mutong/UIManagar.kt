package com.zhuyinge.item.ui.mutong


import org.bukkit.block.Barrel
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import taboolib.common.platform.event.SubscribeEvent

object UIManagar {
    @SubscribeEvent
    fun onRun(event: InventoryClickEvent) {
        val player = event.whoClicked as? Player ?: return
        val inventory = event.inventory
        val view = event.view
        if (inventory.holder is Barrel && view.title == "§6§l装备锻造台") { //§6§l装备锻造台
            val excludedSlots = arrayOf(0, 10, 12, 14, 16, 17)
            if (event.clickedInventory != null && event.clickedInventory?.holder is Barrel) {
                for (slot in 0..26) {
                    if (slot !in excludedSlots && event.slot == slot) {
                        event.isCancelled = true
                    }
                }
            }
        }
    }
}
