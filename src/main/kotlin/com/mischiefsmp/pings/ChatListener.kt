package com.mischiefsmp.pings

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent

class ChatListener: Listener {
    @EventHandler
    fun onChatAsync(event: AsyncPlayerChatEvent) {
        val pl = MischiefPings.pl
        val names = TagUtils.getUsernames(event.message)
        val foundUsers = mutableListOf<Player>()
        names.forEach {
            val p = pl.server.getPlayerExact(it.removePrefix("@"))
            if(p != null) foundUsers.add(p)
        }
        foundUsers.forEach {
            it.sendTitle("Ping!", "You've been pinged by §1${event.player.name}.", 1, 40, 1)
        }
        event.message = TagUtils.colorText(event.message)
    }
}