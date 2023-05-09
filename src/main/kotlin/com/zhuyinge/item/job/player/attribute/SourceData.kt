package com.zhuyinge.item.job.player.attribute


data class SourceData(
    val source: String,
    val attribute: HashMap<String, SourceValue> = hashMapOf(),
    var overTime: Long = -1L,
)