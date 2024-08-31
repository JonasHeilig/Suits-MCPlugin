package de.jonasheilig.suits.items

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.persistence.PersistentDataType

class Bedport {

    fun create(): ItemStack {
        val pendant = ItemStack(Material.EMERALD)
        val meta: ItemMeta = pendant.itemMeta!!

        meta.displayName(Component.text("Bed Teleport", NamedTextColor.DARK_PURPLE))

        meta.isUnbreakable = true
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE)
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES)

        val lore = listOf(
            Component.text("Right-click to teleport to your last bed.", NamedTextColor.GOLD),
        )
        meta.lore(lore)

        val key = NamespacedKey.fromString("bedport")!!
        meta.persistentDataContainer.set(key, PersistentDataType.STRING, "bedport")

        pendant.itemMeta = meta

        return pendant
    }
}