package de.jonasheilig.suits.recipes

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

object CustomRecipeManager {

    fun registerRecipes(plugin: JavaPlugin) {
        Bukkit.addRecipe(IronmanHelmetRecipe.createRecipe(plugin))
        Bukkit.addRecipe(IronmanChestplateRecipe.createRecipe(plugin))
        Bukkit.addRecipe(IronmanLeggingsRecipe.createRecipe(plugin))
        Bukkit.addRecipe(IronmanBootsRecipe.createRecipe(plugin))
        Bukkit.addRecipe(JarvisRecipe.createRecipe(plugin))
        Bukkit.addRecipe(ArkRecipe.createRecipe(plugin))
        Bukkit.addRecipe(JetEngineCoreRecipe.createRecipe(plugin))
        Bukkit.addRecipe(JetEngineRecipe.createRecipe(plugin))
        Bukkit.addRecipe(ExoRecipe.createRecipe(plugin))
        Bukkit.addRecipe(RepulsoCoreRecipe.createRecipe(plugin))
        Bukkit.addRecipe(LaserCoreRecipe.createRecipe(plugin))
        Bukkit.addRecipe(RepulsoToolRecipe.createRecipe(plugin))
        Bukkit.addRecipe(LaserStaffRecipe.createRecipe(plugin))
    }
}
