package de.jonasheilig.suits.items

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.persistence.PersistentDataType

class Tarnstone {

    fun create(): ItemStack {
        val tarnstone = ItemStack(Material.STONE)
        val meta: ItemMeta = tarnstone.itemMeta!!

        meta.displayName(Component.text("Tarnstone", NamedTextColor.GREEN))
        meta.isUnbreakable = true
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE)
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES)

        val lore = listOf(
            Component.text("Make you Invisible when you hold this item.", NamedTextColor.GREEN)
        )
        meta.lore(lore)

        val key = NamespacedKey.fromString("tarnstone")!!
        meta.persistentDataContainer.set(key, PersistentDataType.STRING, "tarnstone")

        tarnstone.itemMeta = meta
        return tarnstone
    }
}
