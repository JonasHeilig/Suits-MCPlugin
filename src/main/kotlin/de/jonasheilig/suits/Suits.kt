package de.jonasheilig.suits

import org.bukkit.plugin.java.JavaPlugin
import de.jonasheilig.suits.listeners.*
import de.jonasheilig.suits.commands.*
import de.jonasheilig.suits.recipes.CustomRecipeManager

class Suits : JavaPlugin() {

    override fun onEnable() {
        logger.info("Suits Plugin enabled")
        // Listeners
        server.pluginManager.registerEvents(IronSuitListener(this), this)
        server.pluginManager.registerEvents(RepulsoToolListener(this), this)
        server.pluginManager.registerEvents(EnergySnowballListener(this), this)
        // Commands
        getCommand("get")?.setExecutor(GetItemCommand())
        getCommand("get")?.tabCompleter = GetItemCommandCompleter()
        // Recipes
        CustomRecipeManager.registerRecipes(this)
    }

    override fun onDisable() {
        logger.info("Suits Plugin disabled")
    }
}
