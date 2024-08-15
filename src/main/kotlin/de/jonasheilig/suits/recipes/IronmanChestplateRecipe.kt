package de.jonasheilig.suits.recipes

import de.jonasheilig.suits.items.IronmanChestplate
import de.jonasheilig.suits.items.ARKReaktor
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.plugin.java.JavaPlugin

object IronmanChestplateRecipe {

    fun createRecipe(plugin: JavaPlugin): ShapedRecipe {
        val recipeKey = NamespacedKey(plugin, "ironman_chestplate_recipe")
        val recipe = ShapedRecipe(recipeKey, IronmanChestplate().create())

        recipe.shape(
            "g g",
            "RAR",
            "NNN"
        )

        recipe.setIngredient('A', ARKReaktor().create().type)
        recipe.setIngredient('N', Material.NETHERITE_INGOT)
        recipe.setIngredient('R', Material.REDSTONE)
        recipe.setIngredient('g', Material.GOLD_INGOT)

        return recipe
    }
}
