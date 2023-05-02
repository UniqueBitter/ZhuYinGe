package com.zhuyinge.item

import com.zhuyinge.item.ui.dz.DuanZao
import org.bukkit.entity.Player
import taboolib.common.platform.command.CommandBody
import taboolib.common.platform.command.CommandHeader
import taboolib.common.platform.command.mainCommand
import taboolib.common.platform.command.subCommand
import taboolib.expansion.createHelper

@CommandHeader("ZhuYinGe", aliases = ["ub"], permission = "panling.use")
object Command {
    @CommandBody(permission = "panling.admin")
    val main = mainCommand {
        createHelper()
    }

    @CommandBody(permission = "panling.dz")
    val dz = subCommand {
        execute<Player> { sender, context, argument ->
            DuanZao.open(sender)
        }
    }

}