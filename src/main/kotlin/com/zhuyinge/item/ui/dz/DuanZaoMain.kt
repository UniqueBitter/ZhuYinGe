package com.zhuyinge.item.ui.dz

import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import java.io.File

object DuanZaoMain {

    fun dzMain() {

    }


    @Awake(LifeCycle.ENABLE)
    fun loadConfig(): FileConfiguration {
        val configFile = File("duanzao.yml")
        if (!configFile.exists()) {
            configFile.createNewFile()
        }
        return YamlConfiguration.loadConfiguration(configFile)
    }
}