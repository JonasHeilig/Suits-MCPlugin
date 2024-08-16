package de.jonasheilig.suits.recipes

import de.jonasheilig.suits.items.RepulsoCore
import de.jonasheilig.suits.items.RepulsoTool
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.plugin.java.JavaPlugin

object RepulsoToolRecipe {
    fun createRecipe(plugin: JavaPlugin): ShapedRecipe {
        val recipeKey = NamespacedKey(plugin, "repulso_tool_recipe")
        val recipe = ShapedRecipe(recipeKey, RepulsoTool().create())

        recipe.shape(
            "NRC",
            " SR",
            "S N"
        )

        recipe.setIngredient('R', Material.REDSTONE)
        recipe.setIngredient('S', Material.STICK)
        recipe.setIngredient('N', Material.NETHERITE_INGOT)
        recipe.setIngredient('C', RepulsoCore().create().type)

        return recipe
    }
}
