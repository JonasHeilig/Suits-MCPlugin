package de.jonasheilig.suits.items

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.persistence.PersistentDataType

class Firestick {

    fun create(): ItemStack {
        val firestick = ItemStack(Material.STICK)
        val meta: ItemMeta = firestick.itemMeta!!

        meta.displayName(Component.text("Firestick", NamedTextColor.RED))
        meta.isUnbreakable = true
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE)
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES)

        val lore = listOf(
            Component.text("Shoot with a right-click fire at your enemy.", NamedTextColor.DARK_RED),

        )
        meta.lore(lore)

        val key = NamespacedKey.fromString("firestick")!!
        meta.persistentDataContainer.set(key, PersistentDataType.STRING, "firestick")

        firestick.itemMeta = meta
        return firestick
    }
}
