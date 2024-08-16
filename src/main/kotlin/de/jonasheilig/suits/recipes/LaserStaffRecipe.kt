package de.jonasheilig.suits.recipes

import de.jonasheilig.suits.items.LaserCore
import de.jonasheilig.suits.items.LaserStaff
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.plugin.java.JavaPlugin

object LaserStaffRecipe {
    fun createRecipe(plugin: JavaPlugin): ShapedRecipe {
        val recipeKey = NamespacedKey(plugin, "laser_staff_recipe")
        val recipe = ShapedRecipe(recipeKey, LaserStaff().create())

        recipe.shape(
            " RC",
            " SR",
            "S  "
        )

        recipe.setIngredient('R', Material.REDSTONE)
        recipe.setIngredient('S', Material.STICK)
        recipe.setIngredient('C', LaserCore().create().type)

        return recipe
    }
}
