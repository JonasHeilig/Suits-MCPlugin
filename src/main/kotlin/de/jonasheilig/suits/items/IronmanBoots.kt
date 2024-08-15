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

class IronmanBoots {

    fun create(): ItemStack {
        val boots = ItemStack(Material.LEATHER_BOOTS)
        val meta: LeatherArmorMeta = boots.itemMeta as LeatherArmorMeta

        meta.displayName(Component.text("Ironman Boots", NamedTextColor.GOLD))
        meta.isUnbreakable = true
        meta.setColor(Color.RED)

        val lore = listOf(
            Component.text("In Combination with the Ironman Chestplate:", NamedTextColor.DARK_BLUE),
            Component.text("Jump Boost", NamedTextColor.BLUE),
            Component.text("Allow Flight", NamedTextColor.BLUE),
            Component.text("Set Bonus: Enhanced Abilities", NamedTextColor.GOLD)
        )
        meta.lore(lore)
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE)

        val key = NamespacedKey.fromString("ironman_boots")!!
        meta.persistentDataContainer.set(key, PersistentDataType.STRING, UUID.randomUUID().toString())

        if (meta is ArmorMeta) {
            val pattern = TrimPattern.EYE
            val material = TrimMaterial.DIAMOND
            val trim = ArmorTrim(material, pattern)
            meta.trim = trim
        }

        boots.itemMeta = meta
        return boots
    }
}
