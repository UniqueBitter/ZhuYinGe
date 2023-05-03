package com.zhuyinge.item.ui.dz

import org.bukkit.Material
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.inventory.ItemStack
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.library.xseries.XMaterial
import taboolib.module.chat.colored
import taboolib.module.ui.openMenu
import taboolib.module.ui.type.Basic
import taboolib.platform.util.buildItem
import java.io.File

@Suppress("ALL")
object DuanZaoUI {

    @Awake(LifeCycle.ENABLE)
    fun loadConfig(): FileConfiguration {
        val configFile = File("duanzao.yml")
        if (!configFile.exists()) {
            configFile.createNewFile()
        }
        return YamlConfiguration.loadConfiguration(configFile)
    }

    fun open(player: Player) {
        player.openMenu<Basic>("&e&l锻造台 ".colored()) {
            map(
                "A########",
                "#B#C#D#EF",
                "########*"
            )
            set('#', buildItem(XMaterial.GRAY_STAINED_GLASS_PANE) {
                name = "§f§l我是墙"
            }) {
                isCancelled = true
            }
            set('*', buildItem(XMaterial.BREWING_STAND) {
                name = "§e§l点我锻造"
                lore.add("§7§o第一行放§2§l装备雏形§b§l/§d§l升级材料")
                lore.add("§7§o第二行第一个格子放§7§l升阶装备§b§l/§c§l传说手稿")
                lore.add("§7§o第二行第二个格子放§4§l武器核心§b§l/§9§l防具核心")
                lore.add("§7§o第二行第三个格子放§a§l元素")
                lore.add("§7§o第二行最后放§e§l区域材料A,B")
            }) {
                isCancelled = true
                // 获取玩家放入锻造材料和装备的情况
                DuanZaoMain.dzMain()
            }
            onClose {
                onMenuClose(it)
            }

        }
    }

    fun onMenuClose(event: InventoryCloseEvent) {

        val player = event.player as? Player ?: return
        val playerInv = player.inventory
        val drops = mutableSetOf<ItemStack>()
        for (slot in arrayOf(0, 10, 12, 14, 16, 17)) {
            val item = event.inventory.getItem(slot) ?: continue
            if (item.type != Material.AIR) {
                if (playerInv.firstEmpty() != -1) {
                    playerInv.addItem(item)
                } else {
                    drops.add(item)
                }
            }
        }
        for (drop in drops) {
            player.world.dropItemNaturally(player.location, drop)
        }
    }
}