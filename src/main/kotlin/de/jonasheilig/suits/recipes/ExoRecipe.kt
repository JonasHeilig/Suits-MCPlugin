package de.jonasheilig.suits.recipes

import de.jonasheilig.suits.items.*
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.plugin.java.JavaPlugin

object ExoRecipe {

    fun createRecipe(plugin: JavaPlugin): ShapedRecipe {
        val recipeKey = NamespacedKey(plugin, "exo_recipe")
        val recipe = ShapedRecipe(recipeKey, EXO().create())

        recipe.shape(
            "NRN",
            "SRS",
            "SRS"
        )

        recipe.setIngredient('R', Material.REDSTONE)
        recipe.setIngredient('S', Material.STICK)
        recipe.setIngredient('N', Material.NETHERITE_INGOT)

        return recipe
    }
}
