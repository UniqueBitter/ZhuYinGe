package com.dew.item.others

import com.dew.item.gui.LingLongYuUI
import de.tr7zw.nbtapi.NBTCompound
import de.tr7zw.nbtapi.NBTItem
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerInteractEvent
import taboolib.common.platform.event.SubscribeEvent
import taboolib.module.ui.openMenu
import taboolib.platform.util.buildItem
import taboolib.platform.util.hasName
import taboolib.platform.util.isLeftClick
import taboolib.platform.util.isRightClick


object LingLongYu {
    fun giveitem(player: Player) {
        var item = buildItem(Material.HONEY_BLOCK) {
            name = "§e玲珑玉"
            lore.add("§e稀有度:★★★★★")
            lore.add("§6推荐职业:[战] [弓] [丹]")
            lore.add("§7§o只为便捷")
        }
        player.inventory.addItem(item)
    }

    @SubscribeEvent
    fun onrun(event:PlayerInteractEvent) {
        val player = event.player
        val item = event.item?:return
        if (event.isRightClick()) {
            if (item.hasName("§e玲珑玉")) {
                LingLongYuUI.open(player)
            }
        }
    }
}