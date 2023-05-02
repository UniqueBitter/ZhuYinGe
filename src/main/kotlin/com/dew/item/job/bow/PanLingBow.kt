package com.dew.item.job.bow
import org.bukkit.inventory.ItemStack
import com.dew.item.utils.getString
object PanLingBow {

    fun isBow(itemStack: ItemStack): Boolean {
        val id = itemStack.getString("id","null")
        return id.contains("bow")
    }

}