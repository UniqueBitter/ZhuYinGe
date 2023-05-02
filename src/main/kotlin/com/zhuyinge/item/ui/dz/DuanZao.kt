package com.zhuyinge.item.ui.dz

import org.bukkit.entity.Player
import taboolib.library.xseries.XMaterial
import taboolib.module.chat.colored
import taboolib.module.ui.openMenu
import taboolib.module.ui.type.Basic
import taboolib.platform.util.buildItem

object DuanZao {


    fun open(player: Player) {
        player.openMenu<Basic>("&e&l锻造台 ".colored()) {
            map(
                "A########",
                "#B#C#D#EF",
                "########*"
            )
            set('#', buildItem(XMaterial.GRAY_STAINED_GLASS_PANE) {}) {
                isCancelled = true
            }
            set('*', buildItem(XMaterial.BREWING_STAND) {
                name = "§e§l点我锻造"
            }) {
                isCancelled = true

            }
        }
    }


}