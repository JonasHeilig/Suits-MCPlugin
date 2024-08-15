package de.jonasheilig.suits.recipes

import de.jonasheilig.suits.items.*
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.plugin.java.JavaPlugin

object IronmanBootsRecipe {

    fun createRecipe(plugin: JavaPlugin): ShapedRecipe {
        val recipeKey = NamespacedKey(plugin, "ironman_boots_recipe")
        val recipe = ShapedRecipe(recipeKey, IronmanBoots().create())

        recipe.shape(
            "GGG",
            "NEN",
            "JRJ"
        )

        recipe.setIngredient('E', JetEngineCore().create().type)
        recipe.setIngredient('R', Material.REDSTONE)
        recipe.setIngredient('N', Material.NETHERITE_INGOT)
        recipe.setIngredient('G', Material.GOLD_INGOT)

        return recipe
    }
}
