package de.jonasheilig.suits.recipes

import de.jonasheilig.suits.items.RepulsoCore
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.plugin.java.JavaPlugin

object RepulsoCoreRecipe {

    fun createRecipe(plugin: JavaPlugin): ShapedRecipe {
        val recipeKey = NamespacedKey(plugin, "repulso_core_recipe")
        val recipe = ShapedRecipe(recipeKey, RepulsoCore().create())

        recipe.shape(
            "RNR",
            "TET",
            "RTR"
        )

        recipe.setIngredient('R', Material.REDSTONE)
        recipe.setIngredient('T', Material.TORCH)
        recipe.setIngredient('E', Material.EMERALD)
        recipe.setIngredient('N', Material.NETHERITE_INGOT)

        return recipe
    }
}
