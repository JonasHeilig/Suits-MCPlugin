package de.jonasheilig.suits.items

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.persistence.PersistentDataType
import java.util.*

class EnergySnowball {
    fun create(): ItemStack {
        val snowball = ItemStack(Material.SNOWBALL)
        val meta: ItemMeta = snowball.itemMeta

        meta.displayName(Component.text("Energy Snowball", NamedTextColor.BLUE))

        val lore = listOf(
            Component.text("Shoots a blinding blast with levitation effect.", NamedTextColor.GOLD),
            Component.text("Deals 10 damage and applies levitation for 2 seconds.", NamedTextColor.DARK_BLUE)
        )
        meta.lore(lore)

        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE)
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES)

        val key = NamespacedKey.fromString("energy_snowball")!!
        meta.persistentDataContainer.set(key, PersistentDataType.STRING, UUID.randomUUID().toString())

        snowball.itemMeta = meta

        return snowball
    }
}
