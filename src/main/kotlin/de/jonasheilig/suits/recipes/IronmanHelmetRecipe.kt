package de.jonasheilig.suits.recipes

import de.jonasheilig.suits.items.*
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.plugin.java.JavaPlugin

object IronmanHelmetRecipe {

    fun createRecipe(plugin: JavaPlugin): ShapedRecipe {
        val recipeKey = NamespacedKey(plugin, "ironman_helmet_recipe")
        val recipe = ShapedRecipe(recipeKey, IronmanHelmet().create())

        recipe.shape(
            "NgN",
            "GJG",
            "gRg"
        )

        recipe.setIngredient('J', JARVIS().create().type)
        recipe.setIngredient('G', Material.GLASS)
        recipe.setIngredient('N', Material.NETHERITE_INGOT)
        recipe.setIngredient('R', Material.REDSTONE)
        recipe.setIngredient('g', Material.GOLD_INGOT)

        return recipe
    }
}
