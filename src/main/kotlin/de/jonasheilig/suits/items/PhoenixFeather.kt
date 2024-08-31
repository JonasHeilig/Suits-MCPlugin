package de.jonasheilig.suits.items

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.persistence.PersistentDataType

class PhoenixFeather {

    fun create(): ItemStack {
        val feather = ItemStack(Material.FEATHER)
        val meta: ItemMeta = feather.itemMeta!!

        meta.displayName(Component.text("Phoenix Feather", NamedTextColor.RED))

        meta.isUnbreakable = true
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE)
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES)

        val lore = listOf(
            Component.text("Revives you instant when have it in your inv.", NamedTextColor.GREEN),
            Component.text("Random teleport on a save position in radius of 40 blocks", NamedTextColor.GREEN),
            Component.text("Destroy after use.", NamedTextColor.RED)
        )
        meta.lore(lore)

        meta.setMaxStackSize(1)

        val key = NamespacedKey.fromString("phoenix_feather")!!
        meta.persistentDataContainer.set(key, PersistentDataType.STRING, "phoenix_feather")

        feather.itemMeta = meta

        return feather
    }
}
