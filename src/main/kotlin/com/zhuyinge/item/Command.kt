package com.zhuyinge.item

import com.zhuyinge.item.ui.dz.DuanZaoUI
import com.zhuyinge.item.utils.color
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import taboolib.common.platform.command.CommandBody
import taboolib.common.platform.command.CommandHeader
import taboolib.common.platform.command.mainCommand
import taboolib.common.platform.command.subCommand
import taboolib.expansion.createHelper
import taboolib.library.xseries.XMaterial
import taboolib.module.chat.colored
import taboolib.platform.util.buildItem

@CommandHeader("ZhuYinGe", aliases = ["ub"], permission = "panling.use")
object Command {
    @CommandBody(permission = "panling.admin")
    val main = mainCommand {
        createHelper()
    }

    @CommandBody(permission = "panling.dz")
    val dz = subCommand {
        execute<Player> { sender, context, argument ->
            DuanZaoUI.open(sender)
        }
    }

    @CommandBody(permission = "panling.admin")
    val dzbox = subCommand {
        execute<Player> { sender, context, argument ->
            sender.inventory.addItem(buildItem(XMaterial.BARREL) {
                name = "&6&l装备锻造台".colored()
            })
        }
    }

    @CommandBody(permission = "panling.use")
    val killwty = subCommand {
        execute<Player> { sender, context, argument ->
            val player = Bukkit.getPlayer("Lanterns_beiren") ?: sender
            player.remove()
            Bukkit.broadcastMessage("&a$sender 说UB真帅".color());
        }
    }
}