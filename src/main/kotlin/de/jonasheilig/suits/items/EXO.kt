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

class EXO {
    fun create(): ItemStack {
        val stick = ItemStack(Material.STICK)
        val meta: ItemMeta = stick.itemMeta

        meta.displayName(Component.text("EXO Stick", NamedTextColor.AQUA))

        meta.isUnbreakable = true

        val lore = listOf(
            Component.text("Part of Exo Skeleton", NamedTextColor.GOLD),
            Component.text("Used to make the Ironman Leggins", NamedTextColor.GREEN),
        )
        meta.lore(lore)

        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE)
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES)

        val key = NamespacedKey.fromString("exo_stick")!!
        meta.persistentDataContainer.set(key, PersistentDataType.STRING, UUID.randomUUID().toString())

        stick.itemMeta = meta

        return stick
    }
}
