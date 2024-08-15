package de.jonasheilig.suits.items

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Color
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ArmorMeta
import org.bukkit.inventory.meta.LeatherArmorMeta
import org.bukkit.inventory.meta.trim.ArmorTrim
import org.bukkit.inventory.meta.trim.TrimMaterial
import org.bukkit.inventory.meta.trim.TrimPattern
import org.bukkit.persistence.PersistentDataType
import java.util.UUID

class IronmanLeggings {

    fun create(): ItemStack {
        val leggings = ItemStack(Material.LEATHER_LEGGINGS)
        val meta: LeatherArmorMeta = leggings.itemMeta as LeatherArmorMeta

        meta.displayName(Component.text("Ironman Leggings", NamedTextColor.GOLD))
        meta.isUnbreakable = true
        meta.setColor(Color.RED)

        val lore = listOf(
            Component.text("In Combination with the Ironman Chestplate:", NamedTextColor.DARK_BLUE),
            Component.text("Speed", NamedTextColor.BLUE),
            Component.text("Set Bonus: Enhanced Abilities", NamedTextColor.GOLD)
        )
        meta.lore(lore)
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE)

        val key = NamespacedKey.fromString("ironman_leggings")!!
        meta.persistentDataContainer.set(key, PersistentDataType.STRING, UUID.randomUUID().toString())

        if (meta is ArmorMeta) {
            val pattern = TrimPattern.TIDE
            val material = TrimMaterial.IRON
            val trim = ArmorTrim(material, pattern)
            meta.trim = trim
        }

        leggings.itemMeta = meta
        return leggings
    }
}
