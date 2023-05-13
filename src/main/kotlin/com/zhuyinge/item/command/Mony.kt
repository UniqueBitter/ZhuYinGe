package com.zhuyinge.item.command

import com.zhuyinge.item.ui.MoneyUI
import org.bukkit.entity.Player
import taboolib.common.platform.command.CommandBody
import taboolib.common.platform.command.CommandHeader
import taboolib.common.platform.command.mainCommand

@CommandHeader("moneyui", permission = "moneyui")
object Mony {
    @CommandBody(permission = "panling.admin")
    val main = mainCommand {
        execute<Player> { sender, context, argument ->
            MoneyUI.open(sender)
        }
    }
}