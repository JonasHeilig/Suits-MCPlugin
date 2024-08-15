package de.jonasheilig.suits.recipes

import de.jonasheilig.suits.items.JARVIS
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.plugin.java.JavaPlugin

object JarvisRecipe {

    fun createRecipe(plugin: JavaPlugin): ShapedRecipe {
        val recipeKey = NamespacedKey(plugin, "jarvis_recipe")
        val recipe = ShapedRecipe(recipeKey, JARVIS().create())

        recipe.shape(
            "RRR",
            "GTG",
            "RRR"
        )

        recipe.setIngredient('G', Material.GLASS)
        recipe.setIngredient('R', Material.REDSTONE)
        recipe.setIngredient('T', Material.TOTEM_OF_UNDYING)

        return recipe
    }
}
