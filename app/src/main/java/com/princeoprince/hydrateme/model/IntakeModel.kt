package com.princeoprince.hydrateme.model

import java.text.SimpleDateFormat

class IntakeModel {
    private val entries: MutableList<IntakeEntry> = ArrayList()

    fun addEntry(entry: IntakeEntry) {
        entries.add(entry)
    }

    fun isTodaysIntakeSufficient(): Boolean = getTodaysIntake() >= 3000

    fun getTodaysIntake(): Int {
        val today = formatDateString(System.currentTimeMillis())
        return getDailyEntries()[today] ?: 0
    }

    private fun getDailyEntries(): Map<String, Int> {
        val map: MutableMap<String, Int> = mutableMapOf()
        for (entry in entries) {
            val date = formatDateString(entry.timeStamp)
            if (!map.containsKey(date)) {
                map[date] = entry.intake
            } else {
                map[date] = map[date]!!.plus(entry.intake)
            }
        }
        return map
    }

    private fun formatDateString(ts: Long): String =
        SimpleDateFormat("dd MMM yyyy").format(ts)

}
