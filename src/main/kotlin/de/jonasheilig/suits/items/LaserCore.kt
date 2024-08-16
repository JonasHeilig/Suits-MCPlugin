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

class LaserCore {
    fun create(): ItemStack {
        val core = ItemStack(Material.REDSTONE)
        val meta: ItemMeta = core.itemMeta

        meta.displayName(Component.text("Laser Core", NamedTextColor.AQUA))

        val lore = listOf(
            Component.text("A core used to craft the Laser Staff.", NamedTextColor.GRAY)
        )
        meta.lore(lore)

        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS)
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES)

        val key = NamespacedKey.fromString("laser_core")!!
        meta.persistentDataContainer.set(key, PersistentDataType.STRING, UUID.randomUUID().toString())

        core.itemMeta = meta

        return core
    }
}
