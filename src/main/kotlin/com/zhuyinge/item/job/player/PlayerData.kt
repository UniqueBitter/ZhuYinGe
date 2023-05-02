package com.zhuyinge.item.job.player

import org.bukkit.Bukkit
import java.util.*

data class PlayerData(
    val target: UUID,
    var job: JobType = JobType.NONE,
    var name: String = "none",
) {
    val player by lazy {
        Bukkit.getPlayer(target)
    }

    fun update() {
        updateJob()
    }

    fun updateJob() {
        player?.let {
            job = PlayerManager.getJob(it)
        }
    }
}

