package com.zhuyinge.item

import taboolib.common.platform.command.CommandBody
import taboolib.common.platform.command.CommandHeader
import taboolib.common.platform.command.mainCommand
import taboolib.expansion.createHelper

@CommandHeader("DewItem", aliases = ["dew"], permission = "panling.use")
object Command {
    @CommandBody(permission = "panling.admin")
    val main = mainCommand {
        createHelper()
    }

}