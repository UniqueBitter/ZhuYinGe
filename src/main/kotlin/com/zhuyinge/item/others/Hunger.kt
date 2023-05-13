package com.zhuyinge.item.others

import org.bukkit.Bukkit
import org.bukkit.attribute.Attribute
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerFishEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import taboolib.common.platform.Schedule
import taboolib.common.platform.event.SubscribeEvent
import java.util.*
import java.util.concurrent.ConcurrentHashMap

object Hunger {

    @Schedule(period = 10)
    fun look() {
        Bukkit.getOnlinePlayers().forEach {
            checkHunger(it)
        }
    }

    val fish = ConcurrentHashMap<UUID, Int>()

    @SubscribeEvent
    fun on(event: PlayerFishEvent) {
        if (event.state != PlayerFishEvent.State.CAUGHT_FISH) {
            return
        }
        val player = event.player

        val fishCount = fish.getOrDefault(player.uniqueId, 0)
        if (player.isOp) {
            return
        }
        fish[player.uniqueId] = fishCount + 1
        if (fishCount >= 5) {
            if (player.foodLevel <= 2) {
                event.isCancelled = true
                return
            }
            player.foodLevel--
            fish[player.uniqueId] = 0
        }
    }

    private fun checkHunger(player: Player) {
        val level = player.level
        val food = player.foodLevel
        if (food != 0 || level <= 20) {
            return
        }
        when (level) {
            in (20..25) -> {
                player.addPotionEffects(
                    listOf(
                        PotionEffect(
                            PotionEffectType.SLOW, 15, 1, false, false, false
                        )
                    )
                )
            }

            in (25..30) -> {
                player.addPotionEffects(
                    listOf(
                        PotionEffect(
                            PotionEffectType.SLOW, 15, 2, false, false, false
                        )
                    )
                )
            }

            in (30..35) -> {
                player.addPotionEffects(
                    listOf(
                        PotionEffect(
                            PotionEffectType.SLOW, 15, 2, false, false, false
                        ),
                        PotionEffect(
                            PotionEffectType.CONFUSION, 15, 1, false, false, false
                        ),
                        PotionEffect(
                            PotionEffectType.WEAKNESS, 15, 1, false, false, false
                        )
                    )
                )
            }

            in (35..50) -> {
                player.addPotionEffects(
                    listOf(
                        PotionEffect(
                            PotionEffectType.SLOW, 15, 2, false, false, false
                        ),
                        PotionEffect(
                            PotionEffectType.CONFUSION, 15, 1, false, false, false
                        ),
                        PotionEffect(
                            PotionEffectType.WEAKNESS, 15, 5, false, false, false
                        ),
                        PotionEffect(
                            PotionEffectType.WITHER, 15, 1, false, false, false
                        )
                    )
                )
            }

            else -> {
                player.addPotionEffects(
                    listOf(
                        PotionEffect(
                            PotionEffectType.SLOW, 15, 5, false, false, false
                        ),
                        PotionEffect(
                            PotionEffectType.BLINDNESS, 15, 1, false, false, false
                        ),
                        PotionEffect(
                            PotionEffectType.WEAKNESS, 15, 5, false, false, false
                        ),
                        PotionEffect(
                            PotionEffectType.WITHER, 30, 20, false, false, false
                        ),
                        PotionEffect(
                            PotionEffectType.HUNGER, 10, 10, false, false, false
                        )
                    )
                )
                val max = player.getAttribute(Attribute.GENERIC_MAX_HEALTH)?.value ?: 20
                val take = max.toDouble() / 10
                player.health = if (player.health - take <= 0) {
                    0.0
                } else {
                    player.health - take
                }
            }

        }
    }


}