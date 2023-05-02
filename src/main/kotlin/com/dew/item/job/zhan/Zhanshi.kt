package com.dew.item.job.zhan

import com.dew.item.job.player.JobType.*
import com.dew.item.job.player.PlayerManager
import org.bukkit.attribute.Attribute
import org.bukkit.entity.*
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import taboolib.common.platform.event.SubscribeEvent
import taboolib.module.chat.colored
import taboolib.platform.util.sendActionBar


object Zhanshi {

    //拦截战士攻击伤害并且加强
    //判断自身血量和怪物血量
    //对低血量怪物进行斩杀,自己血量高度决定自身增益
    @SubscribeEvent
    fun Onpve(event: EntityDamageByEntityEvent) {
        val player = event.damager as? Player ?: return//获取造成伤害的实体
        val mob = event.entity as? LivingEntity ?: return//获取被攻击的实体
        if (PlayerManager.getJob(player) != ZHANSHI) {
            return
        }
        when (mob.type) {
            //骷髅伤害x60 血量低于80斩杀
            EntityType.SKELETON -> {
                if (mob.health <= mob.maxHealth * 0.8) {
                    mob.damage(mob.health)
                    player.sendMessage("&b&l你触发了斩杀，将怪物击杀了！".colored())
                    player.health = player.health +20
                }
                event.damage = event.damage * 60.0
                player.sendActionBar("&b&l你的目标是骷髅，伤害*60".colored())
                return
            }
            //其他x20
            else -> {if (mob.health <= mob.maxHealth / 2) {
                mob.damage(mob.health)
                player.sendMessage("&b&l你触发了斩杀，将怪物击杀了！".colored())
                player.health = player.health +20

            }
                event.damage = event.damage * 5.0
                player.sendActionBar("&b&l未知目标！伤害翻5倍！".colored())
                return
            }
        }


    }
}