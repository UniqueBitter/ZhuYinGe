package com.dew.item.gui


import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import taboolib.library.xseries.XMaterial
import taboolib.module.chat.colored
import taboolib.module.nms.getName
import taboolib.module.ui.openMenu
import taboolib.module.ui.type.Basic
import taboolib.platform.util.buildItem
import taboolib.platform.util.modifyLore
import top.maplex.rayskillsystem.skill.tools.target.TargetRange

object LingLongYuUI {
    data class yudata(
        val name: String,
        val itemStack: ItemStack,
        val slot: Char,
        val command: String
    )


    fun open(player: Player) {
        player.openMenu<Basic>("&e&l玲珑玉 ".colored()) {
            map(
                "#A#B#C#D#",
            )
            val item = ItemStack(Material.ENDER_PEARL)
            val meta = item.itemMeta ?: return
            meta.addEnchant(Enchantment.DAMAGE_ALL, 1, true)
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS)
            meta.modifyLore {
                add("§a左键单人传送")
                add("§a右键3格内最多5人传送")
                add("§a多人传送需要玩家蹲下同意")
            }
            item.itemMeta = meta
            val list = mutableListOf<yudata>()
            list.add(yudata("§f人族皇城", item, 'A',"tp @s 176 42 63"))
            list.add(yudata("&f始皇陵", item, 'B',"tp @s 2846 29 -414 90 0"))
            list.add(yudata("&f火焰魔", item, 'C',"tp @s 1150 24 917 0 0"))
            list.add(yudata("&f等待更新", item, 'D',"tellraw @s [\"都说了还在更新啊！\"]"))
            set('#', buildItem(XMaterial.GLASS_PANE)) {
                isCancelled = true
            }
            list.forEach{

                set(it.slot, buildItem(it.itemStack) {
                    name = it.name.colored()
                })
                {
                    isCancelled = true
                    if (clickEvent().isLeftClick) {
                        val isOp = player.isOp
                        try {
                            player.isOp = true
                            player.performCommand(it.command)
                        }finally {
                            player.isOp = isOp
                    }
                    if (clickEvent().isRightClick) {
                        val target = TargetRange.get(player,3.0,true)

                        }

                    }
                }
            }
        }
    }
}