package de.jonasheilig.suits.items

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.persistence.PersistentDataType

class MinersTear {

    fun create(): ItemStack {
        val tear = ItemStack(Material.GHAST_TEAR)
        val meta: ItemMeta = tear.itemMeta!!

        meta.displayName(Component.text("Miner's Tear", NamedTextColor.DARK_AQUA))

        meta.isUnbreakable = true
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE)
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES)

        val lore = listOf(
            Component.text("Grants Haste while holding this item.", NamedTextColor.GOLD),
            Component.text("Effect lasts while item is in hand.", NamedTextColor.GREEN)
        )
        meta.lore(lore)

        val key = NamespacedKey.fromString("miners_tear")!!
        meta.persistentDataContainer.set(key, PersistentDataType.STRING, "miners_tear")

        meta.setMaxStackSize(1)

        tear.itemMeta = meta

        return tear
    }
}
