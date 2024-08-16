package de.jonasheilig.suits.recipes

import de.jonasheilig.suits.items.LaserCore
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.plugin.java.JavaPlugin

object LaserCoreRecipe {
    fun createRecipe(plugin: JavaPlugin): ShapedRecipe {
        val recipeKey = NamespacedKey(plugin, "laser_core_recipe")
        val recipe = ShapedRecipe(recipeKey, LaserCore().create())

        recipe.shape(
            "RRR",
            "ETE",
            "RER"
        )

        recipe.setIngredient('R', Material.REDSTONE)
        recipe.setIngredient('T', Material.TORCH)
        recipe.setIngredient('E', Material.EMERALD)

        return recipe
    }
}
