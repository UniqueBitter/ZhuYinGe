package com.dew.item.job.zhan

import com.dew.item.job.player.JobType
import com.dew.item.job.player.PlayerManager
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import taboolib.common.platform.event.SubscribeEvent

object Buff {
    @SubscribeEvent
    fun Onpve(event: EntityDamageByEntityEvent) {
        val player = event.damager as? Player ?:return
        if (PlayerManager.getJob(player) != JobType.ZHANSHI) {
            return
        }
        if (player.health<=player.maxHealth*0.7) {
            player.addPotionEffect(PotionEffect(PotionEffectType.FAST_DIGGING,60,1))
        }
        if (player.health<=player.maxHealth*0.5) {
            player.addPotionEffect(PotionEffect(PotionEffectType.SPEED,60,1))
        }
    }
}