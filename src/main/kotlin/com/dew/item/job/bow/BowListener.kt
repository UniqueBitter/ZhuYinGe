package com.dew.item.job.bow

import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.entity.*
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.ProjectileHitEvent
import org.bukkit.event.inventory.ClickType
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.meta.CrossbowMeta
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import taboolib.common.platform.event.EventPriority
import taboolib.common.platform.event.SubscribeEvent
import taboolib.module.chat.colored
import taboolib.platform.util.isAir
import taboolib.platform.util.sendActionBar


object BowListener {

    @SubscribeEvent
    fun e(e: ProjectileHitEvent) {
        if (e.entity is FishHook && (e.hitEntity is ArmorStand || e.hitEntity is Villager || e.hitEntity?.hasMetadata("NPC") == true)) {
            e.entity.remove()
        }
        val arrow = e.entity
        if (arrow is Arrow) {
            if (arrow.pickupStatus != AbstractArrow.PickupStatus.ALLOWED) {
            }
            val shooter = arrow.shooter
            if (shooter is Player) {
                shooter.playSound(shooter.location, Sound.UI_BUTTON_CLICK, 1f, 1f)
                if (arrow.pickupStatus != AbstractArrow.PickupStatus.CREATIVE_ONLY) {
                }
            }
        }
    }

    /**
     * 非创造模式使用鼠标中键点击弩卸载已装备的箭
     */
    @SubscribeEvent(priority = EventPriority.MONITOR, ignoreCancelled = true)
    fun e(e: InventoryClickEvent) {
        if (e.click == ClickType.MIDDLE && e.currentItem?.type == Material.CROSSBOW && e.whoClicked.gameMode != GameMode.CREATIVE && e.cursor.isAir()) {
            val meta = e.currentItem!!.itemMeta as? CrossbowMeta
            if (meta?.chargedProjectiles == null) {
                return
            }
            val charged = meta.chargedProjectiles.toMutableList()
            if (charged.isEmpty()) {
                return
            }
            e.isCancelled = true
            e.whoClicked.setItemOnCursor(charged.removeAt(0))
            e.currentItem!!.itemMeta = meta.run {
                this.setChargedProjectiles(charged)
                this
            }
            (e.whoClicked as Player).playSound(e.whoClicked.location, Sound.ITEM_CROSSBOW_LOADING_END, 1f, 1f)
        }
    }

    @SubscribeEvent
    fun onProjectileHit(event: EntityDamageByEntityEvent) {
        val projectile = event.damager
        if (projectile is Arrow) {
            val arrow = projectile
            if (arrow.shooter is Player) {
                val shooter = arrow.shooter as Player? ?: return

                val hitEntity = event.entity
                shooter.spawnParticle(Particle.CRIT_MAGIC, hitEntity.location, 3)
                if (hitEntity is LivingEntity) {
                    val entityLoc = hitEntity.location
                    val x: Double = entityLoc.x
                    val y: Double = entityLoc.y
                    val z: Double = entityLoc.z
                    val arrowX = arrow.location.x
                    val arrowY = arrow.location.y
                    val arrowZ = arrow.location.z
                    val box = hitEntity.boundingBox.clone()
                    when (hitEntity.type) {
                        EntityType.ZOMBIE, EntityType.ZOMBIFIED_PIGLIN,
                        EntityType.SKELETON, EntityType.WITHER_SKELETON,
                        EntityType.HUSK,
                        -> {
                            if (arrowY - y <= 0.7) {
                                hitEntity.addPotionEffect(
                                    PotionEffect(PotionEffectType.SLOW, 60, 2)
                                )
                                shooter.sendActionBar("&b&l命中腿部！减速目标！".colored())
                                event.damage = event.damage * 5
                                return
                            }
                            if (arrowY - y >= 1.90 && hitEntity.facing == shooter.facing) {
                                shooter.sendActionBar("&c&l命中后脑！直接秒杀！！！".colored())
                                event.damage = 10000.0
                                return
                            }
                            if (arrowY - y >= 1.90) {
                                shooter.sendActionBar("&b&l爆头！高额伤害！".colored())
                                event.damage = event.damage * 10.0
                                return
                            }

                            if (box.centerZ - arrowZ >= 0.25 || arrowZ - box.centerZ >= 0.25) {
                                if (arrowY - y in 1.40..2.5 || y - arrowY in -2.5..-1.40) {
                                    shooter.sendActionBar("&b&l命中胳膊！减少目标伤害！".colored())
                                    event.damage = event.damage * 5
                                    hitEntity.addPotionEffect(
                                        PotionEffect(PotionEffectType.WEAKNESS, 60, 2)

                                    )
                                    return
                                }
                            }
                        }

                        EntityType.BLAZE -> {
                            if (arrowY - y >= 1.5 && hitEntity.facing == shooter.facing) {
                                shooter.sendActionBar("&c&l命中后脑！爆炸伤害！".colored())
                                event.damage = event.damage * 20.0
                                return
                            }
                            if (arrowY - y >= 1.5) {
                                shooter.sendActionBar("&b&l爆头！高额伤害！".colored())
                                event.damage = event.damage * 10.0
                                return
                            }
                        }

                        else -> {}

                    }
                }
            }
        }
    }
}