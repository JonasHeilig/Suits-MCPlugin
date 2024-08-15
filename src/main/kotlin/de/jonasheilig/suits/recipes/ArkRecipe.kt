package de.jonasheilig.suits.recipes

import de.jonasheilig.suits.items.ARKReaktor
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.plugin.java.JavaPlugin

object ArkRecipe {

    fun createRecipe(plugin: JavaPlugin): ShapedRecipe {
        val recipeKey = NamespacedKey(plugin, "ark_recipe")
        val recipe = ShapedRecipe(recipeKey, ARKReaktor().create())

        recipe.shape(
            "RBR",
            "CSC",
            "RCR"
        )

        recipe.setIngredient('R', Material.REDSTONE)
        recipe.setIngredient('C', Material.COPPER_INGOT)
        recipe.setIngredient('B', Material.COPPER_BLOCK)
        recipe.setIngredient('S', Material.NETHER_STAR)

        return recipe
    }
}
