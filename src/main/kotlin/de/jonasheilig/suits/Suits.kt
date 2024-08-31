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
        server.pluginManager.registerEvents(SuperCarrotListener(this), this)
        server.pluginManager.registerEvents(EnergySwordListener(this), this)
        server.pluginManager.registerEvents(FirestickListener(this), this)
        server.pluginManager.registerEvents(LaserStaffListener(this), this)
        server.pluginManager.registerEvents(TimeAcceleratorListener(this), this)
        server.pluginManager.registerEvents(LightningStickListener(this), this)
        server.pluginManager.registerEvents(PhoenixFeatherListener(this), this)
        server.pluginManager.registerEvents(LevitationOrbListener(this), this)
        server.pluginManager.registerEvents(ImpulseBombListener(this), this)
        server.pluginManager.registerEvents(BedportListener(this), this)
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
