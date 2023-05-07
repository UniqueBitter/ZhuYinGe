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
        if (inventory.holder is Barrel && view.title == "§6§l装备锻造台") {
            val excludedSlots = arrayOf(0, 10, 12, 14, 16, 17)
            if (event.clickedInventory != null && event.clickedInventory?.holder is Barrel) {
                for (slot in 0..26) {
                    if (slot !in excludedSlots && event.slot == slot) {
                        val pattern = arrayOf(
                            "A########",
                            "#B#C#D#EF",
                            "########*"
                        )
                        for (row in pattern.indices) {
                            for (col in pattern[row].indices) {
                                if (pattern[row][col] == '#') {
                                    inventory.setItem(row * 9 + col, buildItem(XMaterial.GRAY_STAINED_GLASS_PANE) {
                                        name = "§f§l我是墙"
                                    })
                                }
                                if (pattern[row][col] == '*') {
                                    inventory.setItem(row * 9 + col, buildItem(XMaterial.BREWING_STAND) {
                                        name = "§e§l快速教程"
                                        lore.add("§7§o第一行放§2§l装备雏形§b§l/§d§l升级材料")
                                        lore.add("§7§o第二行第一个格子放§7§l升阶装备§b§l/§c§l传说手稿")
                                        lore.add("§7§o第二行第二个格子放§4§l武器核心§b§l/§9§l防具核心")
                                        lore.add("§7§o第二行第三个格子放§a§l元素")
                                        lore.add("§7§o第二行最后放§e§l区域材料A,B")
                                    })
                                }
                            }
                            if (slot == 26) {
                                event.isCancelled = true
                            } else {
                                event.isCancelled = true
                            }
                        }
                    }
                }
            }
        }
    }
}