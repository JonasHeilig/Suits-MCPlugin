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

class ARKReaktor() {

    fun create(): ItemStack {
        val ark = ItemStack(Material.TORCH)
        val meta: ItemMeta = ark.itemMeta!!

        meta.displayName(Component.text("ARK Reaktor",  NamedTextColor.GOLD))

        meta.isUnbreakable = true
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE)
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES)

        val lore = listOf(
            Component.text("A powerful energy source", NamedTextColor.GOLD),
            Component.text("Used to power the Ironman Suit", NamedTextColor.GREEN),
            Component.text("Needs for the Ironman Chestplate", NamedTextColor.GREEN),
            Component.text("", NamedTextColor.RED),
            Component.text("-----------------------------", NamedTextColor.GRAY),
            Component.text("", NamedTextColor.RED),
            Component.text("If you place, the item will be destroyed", NamedTextColor.RED),
        )
        meta.lore(lore)

        val key = NamespacedKey.fromString("ironman_ark")!!
        meta.persistentDataContainer.set(key, PersistentDataType.STRING, UUID.randomUUID().toString())

        ark.itemMeta = meta

        return ark
    }
}