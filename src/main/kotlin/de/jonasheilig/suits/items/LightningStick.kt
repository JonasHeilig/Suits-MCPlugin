package de.jonasheilig.suits.items

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.persistence.PersistentDataType

class LightningStick {

    fun create(): ItemStack {
        val lightningRod = ItemStack(Material.BLAZE_ROD)
        val meta: ItemMeta = lightningRod.itemMeta!!

        meta.displayName(Component.text("Lightning Rod", NamedTextColor.AQUA))

        meta.isUnbreakable = true
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE)
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES)

        val lore = listOf(
            Component.text("Summon a Lightning on your enemy,", NamedTextColor.GOLD),
            Component.text("Right-click to use with 10 sek. cooldown", NamedTextColor.GREEN)
        )
        meta.lore(lore)

        val key = NamespacedKey.fromString("lightning_rod")!!
        meta.persistentDataContainer.set(key, PersistentDataType.STRING, "lightning_stick")

        lightningRod.itemMeta = meta

        return lightningRod
    }
}
