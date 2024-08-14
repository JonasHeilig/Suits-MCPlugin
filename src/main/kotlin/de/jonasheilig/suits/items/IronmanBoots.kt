package de.jonasheilig.suits.items

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

        meta.setDisplayName("Ironman Boots")
        meta.isUnbreakable = true
        meta.setColor(Color.RED)

        val lore = listOf(
            "In Combination with the Ironman Chestplate:",
            "Jump Boost",
            "Allow Flight",
            "Set Bonus: Enhanced Abilities"
        )
        meta.lore = lore
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
