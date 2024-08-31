package de.jonasheilig.suits.items

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.bukkit.persistence.PersistentDataType

class ExplorersTear {

    fun create(): ItemStack {
        val tear = ItemStack(Material.GHAST_TEAR)
        val meta: ItemMeta = tear.itemMeta!!

        meta.displayName(Component.text("Explorer's Tear", NamedTextColor.GOLD))

        meta.isUnbreakable = true
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE)
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES)

        val lore = listOf(
            Component.text("Grants Speed while holding this item.", NamedTextColor.GOLD),
            Component.text("Effect lasts while item is in hand.", NamedTextColor.GREEN)
        )
        meta.lore(lore)

        val key = NamespacedKey.fromString("explorers_tear")!!
        meta.persistentDataContainer.set(key, PersistentDataType.STRING, "explorers_tear")

        meta.setMaxStackSize(1)

        tear.itemMeta = meta

        return tear
    }
}
