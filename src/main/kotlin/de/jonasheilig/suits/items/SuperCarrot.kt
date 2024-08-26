package de.jonasheilig.suits.items

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.persistence.PersistentDataType
import java.util.*

class SuperCarrot {
    fun create(): ItemStack {
        val carrot = ItemStack(Material.GOLDEN_CARROT)
        val meta: ItemMeta = carrot.itemMeta

        meta.displayName(Component.text("Super Carrot", NamedTextColor.GREEN))

        val lore = listOf(
            Component.text("A Carrot that grants", NamedTextColor.GOLD),
            Component.text("Regeneration, Resistance and Strength.", NamedTextColor.GOLD),
            Component.text("But let you also glow.", NamedTextColor.RED)
        )
        meta.lore(lore)

        meta.enchantmentGlintOverride
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE)
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES)

        val key = NamespacedKey.fromString("super_carrot")!!
        meta.persistentDataContainer.set(key, PersistentDataType.STRING, UUID.randomUUID().toString())

        carrot.itemMeta = meta

        return carrot
    }
}
