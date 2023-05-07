package com.zhuyinge.item


import taboolib.common.platform.Plugin
import taboolib.common.platform.function.info


object ZhuYinGe : Plugin() {

    override fun onEnable() {
        info(
            """§a
        _______    _ _    ___     _______ _   _  _____ ______ 
       |___  / |  | | |  | \ \   / /_   _| \ | |/ ____|  ____|
          / /| |__| | |  | |\ \_/ /  | | |  \| | |  __| |__   
         / / |  __  | |  | | \   /   | | | . ` | | |_ |  __|  
        / /__| |  | | |__| |  | |   _| |_| |\  | |__| | |____ 
       /_____|_|  |_|\____/   |_|  |_____|_| \_|\_____|______|
       =================By Unique_Bitter=====================
    """.trimIndent()
        )
        info("§a[朱音阁] 插件加载完成")


    }

}