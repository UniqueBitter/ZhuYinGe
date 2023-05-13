package com.zhuyinge.item.ui


import com.zhuyinge.item.utils.buildSXItem
import com.zhuyinge.item.utils.getSXItem
import com.zhuyinge.item.utils.itemManager
import github.saukiya.sxitem.SXItem
import me.yic.xconomy.api.XConomyAPI
import org.bukkit.entity.Player
import taboolib.common.platform.function.submit
import taboolib.module.chat.colored
import taboolib.module.ui.openMenu
import taboolib.module.ui.type.Basic
import taboolib.platform.util.giveItem
import taboolib.platform.util.takeItem
import kotlin.math.floor


object MoneyUI {

    val api by lazy {
        XConomyAPI()
    }

    data class SlotData(
        val name: String,
        val id: String,
        val slot: Char,
        val value: Double,
    )

    fun open(player: Player) {
        player.openMenu<Basic>("&e&l 朝露-星链  &f(&e${has(player)}&f)".colored()) {
            map(
                "#########",
                "##A#B#C##",
                "#########",
            )
            val list = mutableListOf<SlotData>()
            list.add(SlotData("&f铜钱", "货币_铜钱", 'A', 1.0))
            list.add(SlotData("&a金元宝", "货币_元宝", 'B', 10.0))
            list.add(SlotData("&3银票", "货币_银票", 'C', 100.0))

            list.forEach {
                set(it.slot, buildSXItem(player, it.id) {
                    name = it.name
                    lore.add("&a+ &f左键 取出 &e1")
                    lore.add("&a+ &fSHIFT+左键 取出 &e64")
                    lore.add("&c- &f右键 存入 &e1")
                    lore.add("&c- &fSHIFT+右键 存入 &e64")
                    colored()
                }) {
                    isCancelled = true
                    if (clickEvent().isLeftClick) {
                        if (clickEvent().isShiftClick) {
                            getItem(player, it.id, 64, it.value)
                            reset(player)
                            return@set
                        }
                        if (has(player) < 1) {
                            reset(player)
                            return@set
                        }
                        getItem(player, it.id, 1, it.value)
                        reset(player)
                        return@set
                    }
                    if (clickEvent().isRightClick) {
                        if (clickEvent().isShiftClick) {
                            takeItem(player, it.id, 64, it.value)
                            reset(player)
                            return@set
                        }
                        takeItem(player, it.id, 1, it.value)
                        reset(player)
                        return@set
                    }
                }
            }
        }
    }


    fun getItem(player: Player, name: String, amount: Int, value: Double) {
        val abo = has(player)

        var giveValue = amount * value

        var getAmount = amount
        if (abo <= 0.0 || abo < value) {
            player.sendMessage("&c你的余额不足".colored())
            return
        }

        if (abo <= giveValue) {

            val old = abo / value
            getAmount = floor(old).toInt()
            giveValue = getAmount * value
        }


        player.giveItem(SXItem.getItemManager().getItem(name, player).apply {
            this?.amount = getAmount
        })
        api.changePlayerBalance(player.uniqueId, player.name, (giveValue).toBigDecimal(), false)
    }

    fun takeItem(player: Player, name: String, amount: Int, value: Double) {
        val has = player.getSXItem(name)
        if (has == 0) {
            player.sendMessage("&c你身上并没有这么多钱".colored())
            return
        }
        var hasAmount = amount
        if (has <= amount) {
            hasAmount = has
        }
        val getValue = value * hasAmount
        player.takeSXItem(name, hasAmount)
        api.changePlayerBalance(player.uniqueId, player.name, (getValue).toBigDecimal(), true)
    }

    fun reset(player: Player) {
        submit(delay = 1) {
            open(player)
        }
    }

    fun has(player: Player): Double {
        val data = api.getPlayerData(player.uniqueId)
        if (data == null) {
            api.createPlayerData(player.uniqueId, player.name)
        }
        return data.balance.toDouble()
    }

    fun Player.takeSXItem(sxID: String, amount: Int = 1): Boolean {
        return inventory.takeItem(amount) {
            val build = itemManager.getItem(sxID, this)
            build!!.isSimilar(it)
        }
    }

}