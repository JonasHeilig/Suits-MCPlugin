package de.jonasheilig.suits.recipes

import de.jonasheilig.suits.items.*
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.plugin.java.JavaPlugin

object JetEngineCoreRecipe {

    fun createRecipe(plugin: JavaPlugin): ShapedRecipe {
        val recipeKey = NamespacedKey(plugin, "jet_engine_core_recipe")
        val recipe = ShapedRecipe(recipeKey, JetEngineCore().create())

        recipe.shape(
            "NRN",
            "RSR",
            "NJN"
        )

        recipe.setIngredient('R', Material.REDSTONE)
        recipe.setIngredient('J', JetEngine().create().type)
        recipe.setIngredient('S', Material.NETHER_STAR)
        recipe.setIngredient('N', Material.NETHERITE_INGOT)

        return recipe
    }
}
