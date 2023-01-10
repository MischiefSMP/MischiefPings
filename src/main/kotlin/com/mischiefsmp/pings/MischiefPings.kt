package com.mischiefsmp.pings

import org.bukkit.plugin.java.JavaPlugin

class MischiefPings: JavaPlugin() {
    init {
        pl = this
    }

    override fun onEnable() {
        pl.server.pluginManager.registerEvents(ChatListener(), pl)
    }

    companion object {
        lateinit var pl: MischiefPings
    }
}