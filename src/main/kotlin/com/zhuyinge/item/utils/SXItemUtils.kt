package com.zhuyinge.item.utils

import github.saukiya.sxitem.SXItem
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import taboolib.module.chat.colored
import taboolib.platform.util.*

val itemManager by lazy {
    SXItem.getItemManager()
}

fun String.getName(): String {
    val ig = itemManager.getGenerator(this) ?: return "null"
    return ig.config.getString("NBT.lotus.name")?.colored() ?: "null"
}

fun getSXitem(player: Player, id: String): ItemStack? {
    return itemManager.getItem(id, player)
}

fun buildSXItem(player: Player, sxID: String, builder: ItemBuilder.() -> Unit = {}): ItemStack {
    return buildItem(itemManager.getItem(sxID, player) ?: ItemStack(Material.APPLE), builder)
}

fun Player.giveSXItem(id: String, amount: Int = 1, vararg info: String) {
    giveItem(itemManager.getItem(id, this, info), amount)
}

fun Player.takeSXItem(sxID: String, amount: Int = 1): Boolean {
    return inventory.takeItem(amount) {
        val build = itemManager.getItem(sxID, this)
        build!!.isSimilar(it)
    }
}

fun Player.hasSXItem(sxID: String, amount: Int = 1): Boolean {
    return inventory.hasItem(amount) {
        val build = itemManager.getItem(sxID, this)
        build!!.isSimilar(it)
    }
}

fun Player.hasSXItem(map: Map<String, Int>): Boolean {
    var temp: Boolean
    map.forEach { (t, u) ->
        temp = inventory.hasItem(u) {
            val manager = itemManager.getGenerator(it)
            if (manager != null) {
                manager.key == t
            } else {
                false
            }
        }
        if (!temp) {
            return false
        }
    }
    return true
}

fun Player.hasSXItem(map: Map<String, Int>, action: Player.() -> Unit): Boolean {
    var temp: Boolean
    map.forEach { (t, u) ->
        temp = inventory.hasItem(u) {
            val manager = itemManager.getGenerator(it)
            if (manager != null) {
                manager.key == t
            } else {
                false
            }
        }
        if (!temp) {
            return false
        }
    }
    action.invoke(this)
    return true
}

fun Player.takeSXItem(map: Map<String, Int>, action: (Player.() -> Unit)? = null): Boolean {
    var temp: Boolean
    map.forEach { (t, u) ->
        temp = inventory.hasItem(u) {
            val manager = itemManager.getGenerator(it)
            if (manager != null) {
                manager.key == t
            } else {
                false
            }
        }
        if (!temp) {
            return false
        }
    }
    map.forEach { (t, u) ->
        inventory.takeItem(u) {
            val build = itemManager.getItem(t, this)
            build!!.isSimilar(it)
        }
    }
    action?.invoke(this)
    return true
}

fun Player.getSXItem(sxID: String): Int {
    return inventory.countItem {
        val build = itemManager.getItem(sxID, this)
        build!!.isSimilar(it)
    }
}