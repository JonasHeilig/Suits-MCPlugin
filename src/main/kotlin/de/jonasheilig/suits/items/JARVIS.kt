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

class JARVIS() {

    fun create(): ItemStack {
        val jarvis = ItemStack(Material.TOTEM_OF_UNDYING)
        val meta: ItemMeta = jarvis.itemMeta!!

        meta.displayName(Component.text("JARVIS", NamedTextColor.GOLD))

        meta.isUnbreakable = true
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE)
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES)

        val lore = listOf(
            Component.text("A powerful AI assistant", NamedTextColor.GOLD),
            Component.text("Needs for the Ironman Helmet", NamedTextColor.GREEN),
            Component.text("Used to make the Ironman Helm", NamedTextColor.GREEN),
            Component.text("", NamedTextColor.RED),
            Component.text("-----------------------------", NamedTextColor.GRAY),
            Component.text("", NamedTextColor.RED),
            Component.text("Not an actual totem", NamedTextColor.RED),
        )
        meta.lore(lore)

        val key = NamespacedKey.fromString("ironman_jarvis")!!
        meta.persistentDataContainer.set(key, PersistentDataType.STRING, UUID.randomUUID().toString())

        jarvis.itemMeta = meta

        return jarvis
    }
}
