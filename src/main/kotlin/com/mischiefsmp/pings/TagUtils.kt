package com.mischiefsmp.pings

import org.bukkit.entity.Player

object TagUtils {
    private const val tagRegex = "(?<!\\S)(@[^@]*?)(?=[ .?,!]|\$)"
    private const val hereRegex = "(?<=@here\\()(.*)(?=\\))"
    private val pl = MischiefPings.pl

    fun colorText(text: String): String {
        return Regex(tagRegex).replace(text){
            val rawValue = it.value.removePrefix("@")
            val p = pl.server.getPlayerExact(rawValue)
            if(p == null && rawValue != "everyone" && rawValue != "someone" && rawValue != "here") it.value
            else "§e${it.value}§r"
        }
    }

    fun getUsers(text: String, player: Player): List<Player> {
        val list = mutableListOf<Player>()
        Regex(tagRegex).findAll(text).forEach {
            val rawValue = it.value.lowercase().removePrefix("@")
            if(rawValue.startsWith("here")) {
                //TODO: Verify that this actually works
                val range = Regex(hereRegex).find("@$rawValue")?.value?.toIntOrNull() ?: 100
                 player.world.players.forEach { other ->
                    if(other.uniqueId == player.uniqueId) return@forEach
                    val l1 = other.location
                    l1.y = 0.0
                    val l2 = other.location
                    l2.y = 0.0
                    if(l1.distance(l2) <= range)
                        list.add(other)
                }
                return list
            }
            when(rawValue) {
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