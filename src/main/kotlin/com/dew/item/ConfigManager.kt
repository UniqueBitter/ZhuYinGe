package com.dew.item

import com.dew.item.blackitems.BlackItem
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

object ConfigManager {

    fun read() {
        val blackitems = File("blackitems.yml")
        val config = YamlConfiguration.loadConfiguration(blackitems)
        val blacklist = mutableListOf<BlackItem>()

        if (config.isList("blacklist")) {
            for (entry in config.getList("blacklist")!!) {
                if (entry is Map<*, *>) {
                    val id = entry["id"]
                    val material = entry["material"]
                    if (id is String && material is String) {
                        val blackItem = BlackItem(id, material)
                        blacklist.add(blackItem)
                    }
                }
            }
        }
    }
}