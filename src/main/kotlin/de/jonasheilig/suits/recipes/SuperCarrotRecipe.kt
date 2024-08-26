package de.jonasheilig.suits.recipes

import de.jonasheilig.suits.items.SuperCarrot
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.plugin.java.JavaPlugin

object SuperCarrotRecipe {
    fun createRecipe(plugin: JavaPlugin): ShapedRecipe {
        val recipeKey = NamespacedKey(plugin, "super_carrot_recipe")
        val recipe = ShapedRecipe(recipeKey, SuperCarrot().create())

        recipe.shape(
            "GRG",
            "RCR",
            "GRG"
        )

        recipe.setIngredient('G', Material.GOLD_INGOT)
        recipe.setIngredient('C', Material.GOLDEN_CARROT)
        recipe.setIngredient('R', Material.REDSTONE_BLOCK)

        return recipe
    }
}
