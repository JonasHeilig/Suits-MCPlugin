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

class JetEngine {
    fun create(): ItemStack {
        val emerald = ItemStack(Material.EMERALD)
        val meta: ItemMeta = emerald.itemMeta

        meta.displayName(Component.text("Jet Engine Core", NamedTextColor.GREEN))

        meta.isUnbreakable = true

        val lore = listOf(
            Component.text("Part for the JetEngine", NamedTextColor.GOLD),
            Component.text("Used to make the Ironman Boots", NamedTextColor.GREEN),
        )
        meta.lore(lore)

        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE)
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES)

        val key = NamespacedKey.fromString("jet_engine_core")!!
        meta.persistentDataContainer.set(key, PersistentDataType.STRING, UUID.randomUUID().toString())

        emerald.itemMeta = meta

        return emerald
    }
}
