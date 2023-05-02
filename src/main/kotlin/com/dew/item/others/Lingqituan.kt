package com.dew.item.others


import com.dew.item.utils.Cooldown
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.inventory.ItemStack
import taboolib.common.platform.event.SubscribeEvent
import taboolib.platform.util.modifyLore

object Lingqituan {


    @SubscribeEvent
    fun onkill(event: PlayerDeathEvent) {
        val player = event.entity as? Player ?: return
        val item = ItemStack(Material.ENDER_PEARL)
        val itemmeta = item.itemMeta ?: return
        itemmeta.setDisplayName("§e灵气团")
        itemmeta.modifyLore {
            add("§e稀有度:★★★★★")
            add("§7§oMr_me妖体修炼完全后,")
            add("§7§o自然产生的灵气")
            add("§7§o右键丢出后可以将自身传送到落点")
        }
        item.setItemMeta(itemmeta)
        if (player.name == "_Mr_me_") {
            if (Cooldown.checkT("drop", 60 * 60 * 20)) {
                event.drops.add(item)
                player.sendMessage("§a你掉落了灵气团......")
                player.gameMode = GameMode.ADVENTURE

            }
            //player.location.world!!.dropItem(player.location,item)
        }
    }

    /*
    @Schedule(period = 20)
    fun mode() {
        Bukkit.getOnlinePlayers().forEach { player ->
            if (player.gameMode != GameMode.ADVENTURE && !player.isOp) {
                player.gameMode = GameMode.ADVENTURE
            }
        }
    }
*/
}