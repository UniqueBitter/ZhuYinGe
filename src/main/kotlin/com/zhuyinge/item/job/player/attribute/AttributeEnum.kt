package com.zhuyinge.item.job.player.attribute

enum class AttributeEnum(val display: String, val max: Double) {

    QIANGDU_JINZHAN("近战强度", Double.MAX_VALUE),
    QIANGDU_ZHENFA("阵法强度", Double.MAX_VALUE),
    QIANGDU_JIANSHI("箭矢强度", Double.MAX_VALUE),
    QIANGDU_JINGONG("进攻强度", Double.MAX_VALUE),
    COOLDOWN_SPEED("冷却", 100.0),
    BUXIAOHAO("阵法不消耗概率", 100.0),
    DAMAGE_ZHUIZHONG("最终伤害", Double.MAX_VALUE),
    DEFENDS_ALL("全伤害减伤", 80.0),
    DEFENDS_BOW("箭矢伤害减伤", 80.0),
    DEFENDS_FIRE("火焰伤害减伤", 100.0),
    DEFENDS_FIRE_TIME("燃烧时间减免", 100.0),
    DAMAGE_REBOUND("荆棘", Double.MAX_VALUE),
    MOVE_SPEED("移动速度", 100.0),
    JITUIKANGXING("击退抗性", 100.0),
}