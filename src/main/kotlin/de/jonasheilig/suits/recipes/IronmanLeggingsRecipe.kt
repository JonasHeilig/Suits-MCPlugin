package de.jonasheilig.suits.recipes

import de.jonasheilig.suits.items.*
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.plugin.java.JavaPlugin

object IronmanLeggingsRecipe {

    fun createRecipe(plugin: JavaPlugin): ShapedRecipe {
        val recipeKey = NamespacedKey(plugin, "ironman_leggings_recipe")
        val recipe = ShapedRecipe(recipeKey, IronmanLeggings().create())

        recipe.shape(
            "NRN",
            "R R",
            "E E"
        )

        recipe.setIngredient('E', EXO().create().type)
        recipe.setIngredient('R', Material.REDSTONE)
        recipe.setIngredient('N', Material.NETHERITE_INGOT)

        return recipe
    }
}
