package com.zhuyinge.item.others

import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.entity.Wolf
import org.bukkit.event.entity.EntityDamageByEntityEvent
import taboolib.common.platform.event.SubscribeEvent

object Dogkill {
    @SubscribeEvent
    fun onEntityDeath(event: EntityDamageByEntityEvent) {
        val wolf = event.damager as? Wolf ?: return
        val entity = event.entity as? LivingEntity ?: return
        if (wolf.isTamed) {
            val owner = wolf.owner as? Player ?: return
            event.isCancelled = true
            entity.damage(event.damage, owner)
            //try {
            //    val damagerField: Field = event.javaClass.getDeclaredField("damager")
            //    damagerField.isAccessible = true
            //    damagerField.set(event, owner)
            //} catch (e: Exception) {
            //    e.printStackTrace()
            //}
        }


    }
}
