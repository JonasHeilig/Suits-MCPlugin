package de.jonasheilig.suits.items

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.persistence.PersistentDataType
import java.util.UUID

class RepulsoCore {
    fun create(): ItemStack {
        val item = ItemStack(Material.DIAMOND)
        val meta: ItemMeta = item.itemMeta

        meta.displayName(Component.text("Repulso Core", NamedTextColor.BLUE))

        val lore = listOf(
            Component.text("A core component for crafting Repulso tools.", NamedTextColor.GOLD),
            Component.text("Contains powerful magic.", NamedTextColor.DARK_BLUE)
        )
        meta.lore(lore)

        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE)
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES)

        val key = NamespacedKey.fromString("repulso_core")!!
        meta.persistentDataContainer.set(key, PersistentDataType.STRING, UUID.randomUUID().toString())

        item.itemMeta = meta

        return item
    }
}
