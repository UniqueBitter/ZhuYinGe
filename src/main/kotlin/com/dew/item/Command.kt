package com.dew.item

import com.dew.item.gui.Bank
import com.dew.item.gui.LingLongYuUI
import com.dew.item.others.LingLongYu
import org.bukkit.entity.Player
import org.bukkit.inventory.PlayerInventory
import taboolib.common.platform.command.*
import taboolib.expansion.createHelper

@CommandHeader("DewItem", aliases = ["dew"], permission = "panling.use")
object Command {
    @CommandBody(permission = "panling.admin")
    val main = mainCommand {
        createHelper()
    }

    @CommandBody(permission = "panling.money")
    val moneyUI = subCommand {
        execute<Player> { sender, context, argument ->
            Bank.open(sender)
        }
    }
    @CommandBody(permission = "panling.lly")
    val linglongyu = subCommand {
        execute<Player> { sender, context, argument ->
            LingLongYuUI.open(sender)
        }
    }

    @CommandBody(permission = "panling.llyitem")
    val linglongyuitem = subCommand {
        execute<Player> { sender, context, argument ->
            LingLongYu.giveitem(sender)
        }
    }

}