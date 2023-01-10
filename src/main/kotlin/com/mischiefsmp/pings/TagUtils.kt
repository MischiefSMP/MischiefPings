package com.mischiefsmp.pings

import org.bukkit.entity.Player

object TagUtils {
    private const val tagRegex = "(?<!\\S)(@[^@]*?)(?=[ .?,!]|\$)"
    private val pl = MischiefPings.pl

    fun colorText(text: String): String {
        return Regex(tagRegex).replace(text){
            val rawValue = it.value.removePrefix("@")
            val p = pl.server.getPlayerExact(rawValue)
            if(p == null && rawValue != "everyone" && rawValue != "someone") it.value
            else "§e${it.value}§r"
        }
    }

    fun getUsers(text: String): List<Player> {
        val list = mutableListOf<Player>()
        Regex(tagRegex).findAll(text).forEach {
            val rawValue = it.value.lowercase().removePrefix("@")
            when(it.value.lowercase().removePrefix("@")) {
                "everyone" -> return pl.server.onlinePlayers.toList()
                "someone" -> return listOf(pl.server.onlinePlayers.random())
                else -> pl.server.getPlayer(rawValue)?.let { player ->
                    list.add(player)
                }
            }
        }

        return list
    }
}