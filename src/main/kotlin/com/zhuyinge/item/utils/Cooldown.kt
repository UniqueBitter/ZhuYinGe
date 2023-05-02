package com.zhuyinge.item.utils

import java.util.concurrent.ConcurrentHashMap

object Cooldown {

    val map = ConcurrentHashMap<String, Long>()

    fun getLastTime(key: String): Long {
        return (map.getOrDefault(key, 0L) - System.currentTimeMillis()) / 1000
    }

    fun check(key: String, tick: Long): Boolean {
        val now = System.currentTimeMillis()
        if (map[key] == null) {
            map[key] = now + (tick * 1000 / 20)
            return false
        }
        if (map[key]!! <= now) {
            map[key] = now + (tick * 1000 / 20)
            return true
        }
        return false
    }

    fun checkT(key: String, tick: Long): Boolean {
        val now = System.currentTimeMillis()
        if (map[key] == null) {
            map[key] = now + (tick * 1000 / 20)
            return true
        }
        if (map[key]!! <= now) {
            map[key] = now + (tick * 1000 / 20)
            return true
        }
        return false
    }

}