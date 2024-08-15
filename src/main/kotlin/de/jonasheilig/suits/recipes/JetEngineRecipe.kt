package de.jonasheilig.suits.recipes

import de.jonasheilig.suits.items.*
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.plugin.java.JavaPlugin

object JetEngineRecipe {

    fun createRecipe(plugin: JavaPlugin): ShapedRecipe {
        val recipeKey = NamespacedKey(plugin, "jet_engine_recipe")
        val recipe = ShapedRecipe(recipeKey, JetEngine().create())

        recipe.shape(
            "RNR",
            "RER",
            "TTT"
        )

        recipe.setIngredient('R', Material.REDSTONE)
        recipe.setIngredient('T', Material.TORCH)
        recipe.setIngredient('E', Material.EMERALD)
        recipe.setIngredient('N', Material.NETHERITE_INGOT)

        return recipe
    }
}
