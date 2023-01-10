package com.mischiefsmp.pings

object TagUtils {
    private const val tagRegex = "(?<!\\S)(@[^@]*?)(?=[ .?,!]|\$)"

    fun colorText(text: String): String {
        val pl = MischiefPings.pl
        return Regex(tagRegex).replace(text){
            val p = pl.server.getPlayerExact(it.value.removePrefix("@"))
            if(p == null) it.value
            else "§2${it.value}§r"
        }
    }

    fun getUsernames(text: String): List<String> {
        val list = mutableListOf<String>()
        Regex(tagRegex).findAll(text).forEach { list.add(it.value) }
        return list
    }
}