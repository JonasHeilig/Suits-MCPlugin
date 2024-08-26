package de.jonasheilig.suits.recipes

import de.jonasheilig.suits.items.EnergySnowball
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.plugin.java.JavaPlugin

object EnergySnowballRecipe {
    fun createRecipe(plugin: JavaPlugin): ShapedRecipe {
        val recipeKey = NamespacedKey(plugin, "energy_snowball_recipe")
        val recipe = ShapedRecipe(recipeKey, EnergySnowball().create())

        recipe.shape(
            " R ",
            " S ",
            " S "
        )

        recipe.setIngredient('S', Material.SNOWBALL)
        recipe.setIngredient('R', Material.REDSTONE)

        return recipe
    }
}
