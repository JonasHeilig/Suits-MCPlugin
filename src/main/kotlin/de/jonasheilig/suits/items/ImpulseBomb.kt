package de.jonasheilig.suits.items

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.persistence.PersistentDataType

class ImpulseBomb {

    fun create(): ItemStack {
        val bomb = ItemStack(Material.TNT)
        val meta: ItemMeta = bomb.itemMeta!!

        meta.displayName(Component.text("Impulse Bomb", NamedTextColor.RED))

        meta.isUnbreakable = true
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE)
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES)

        val lore = listOf(
            Component.text("Instantly kills all mobs within", NamedTextColor.GOLD),
            Component.text("a radius of 5 blocks.", NamedTextColor.GREEN),
            Component.text("Right-click to activate.", NamedTextColor.RED)
        )
        meta.lore(lore)

        val key = NamespacedKey.fromString("impulse_bomb")!!
        meta.persistentDataContainer.set(key, PersistentDataType.STRING, "impulse_bomb")

        bomb.itemMeta = meta

        meta.setMaxStackSize(1)

        return bomb
    }
}
