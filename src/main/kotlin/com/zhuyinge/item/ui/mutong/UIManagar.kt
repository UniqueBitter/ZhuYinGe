package com.zhuyinge.item.ui.mutong


import org.bukkit.block.Barrel
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import taboolib.common.platform.event.SubscribeEvent
import taboolib.library.xseries.XMaterial
import taboolib.platform.util.buildItem

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
                        when (slot) {
                            in 0..25 -> {
                                view.setItem(slot, buildItem(XMaterial.GLASS_PANE) {
                                    name = "§7§o墙"
                                })
                            }

                            26 -> {
                                view.setItem(slot, buildItem(XMaterial.BREWING_STAND) {
                                    name = "§e§l帮助"
                                    lore.add("§7§o第一行放§2§l装备雏形§b§l/§d§l升级材料")
                                    lore.add("§7§o第二行第一个格子放§7§l升阶装备§b§l/§c§l传说手稿")
                                    lore.add("§7§o第二行第二个格子放§4§l武器核心§b§l/§9§l防具核心")
                                    lore.add("§7§o第二行第三个格子放§a§l元素")
                                    lore.add("§7§o第二行最后放§e§l区域材料A,B")
                                })
                            }
                        }
                    }
                }
            }
        }
    }
}
