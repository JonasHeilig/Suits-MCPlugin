package de.jonasheilig.suits.items

import org.bukkit.ChatColor
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

class IronmanHelmet {

    fun create(): ItemStack {
        val helmet = ItemStack(Material.LEATHER_HELMET)
        val meta: LeatherArmorMeta = helmet.itemMeta as LeatherArmorMeta

        meta.setDisplayName("${ChatColor.GOLD}Ironman Helmet")
        meta.isUnbreakable = true

        val lore = listOf(
            "${ChatColor.DARK_BLUE}In Combination with the Ironman Chestplate:",
            "${ChatColor.BLUE}Player Visibility",
            "${ChatColor.BLUE}Player Holograms(What is this?)",
            "${ChatColor.GOLD}Set Bonus: Enhanced Abilities"
        )
        meta.lore = lore
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE)
        meta.setColor(Color.RED)

        val key = NamespacedKey.fromString("ironman_helmet")!!
        meta.persistentDataContainer.set(key, PersistentDataType.STRING, UUID.randomUUID().toString())

        if (meta is ArmorMeta) {
            val pattern = TrimPattern.EYE
            val material = TrimMaterial.GOLD
            val trim = ArmorTrim(material, pattern)
            meta.trim = trim
        }

        helmet.itemMeta = meta
        return helmet
    }
}
