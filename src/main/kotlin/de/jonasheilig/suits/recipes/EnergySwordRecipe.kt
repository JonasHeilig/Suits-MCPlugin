package de.jonasheilig.suits.recipes

import de.jonasheilig.suits.items.ARKReaktor
import de.jonasheilig.suits.items.EnergySword
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.plugin.java.JavaPlugin

object EnergySwordRecipe {
    fun createRecipe(plugin: JavaPlugin): ShapedRecipe {
        val recipeKey = NamespacedKey(plugin, "energy_sword_recipe")
        val recipe = ShapedRecipe(recipeKey, EnergySword().create())

        recipe.shape(
            " R ",
            "EAE",
            " S "
        )

        recipe.setIngredient('R', Material.REDSTONE)
        recipe.setIngredient('S', Material.STICK)
        recipe.setIngredient('E', Material.EMERALD)
        recipe.setIngredient('A', ARKReaktor().create().type)

        return recipe
    }
}
