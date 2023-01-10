package com.mischiefsmp.pings

object TagUtils {
    private const val tagRegex = "(?<!\\S)(@[^@]*?)(?=[ .?,!]|\$)"

    fun colorText(text: String): String {
        return Regex(tagRegex).replace(text){
            "§2${it.value}§r"
        }
    }

    fun getUsernames(text: String): List<String> {
        val list = mutableListOf<String>()
        Regex(tagRegex).findAll(text).forEach { list.add(it.value) }
        return list
    }
}