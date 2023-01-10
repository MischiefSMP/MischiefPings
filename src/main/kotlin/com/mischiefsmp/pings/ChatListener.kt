package com.mischiefsmp.pings

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent

class ChatListener: Listener {
    @EventHandler
    fun onChatAsync(event: AsyncPlayerChatEvent) {
        val pl = MischiefPings.pl
        val names = TagUtils.getUsernames(event.message)
        println(names)
        val foundUsers = names.filter { pl.server.getPlayerExact(it) != null }
        event.message = TagUtils.colorText(event.message)
    }
}