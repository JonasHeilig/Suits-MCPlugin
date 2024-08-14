package de.jonasheilig.suits

import org.bukkit.plugin.java.JavaPlugin
import de.jonasheilig.suits.listeners.*
import de.jonasheilig.suits.commands.*

class Suits : JavaPlugin() {

    override fun onEnable() {
        logger.info("Suits Plugin enabled")
        // Listeners
        server.pluginManager.registerEvents(IronSuitListener(this), this)
        // Commands
        server.getPluginCommand("get")?.setExecutor(GetItemCommand())
        this.getCommand("get")?.tabCompleter = GetItemCommandCompleter()
        // Recipes
    }

    override fun onDisable() {
        logger.info("Suits Plugin disabled")
    }
}
