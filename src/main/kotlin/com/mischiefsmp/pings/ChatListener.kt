package com.mischiefsmp.pings

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent

class ChatListener: Listener {
    @EventHandler
    fun onChatAsync(event: AsyncPlayerChatEvent) {
        TagUtils.getUsers(event.message).forEach {
            it.sendTitle("Ping!", "You've been pinged by §1${event.player.name}.", 1, 40, 1)
        }
        event.message = TagUtils.colorText(event.message)
    }
}