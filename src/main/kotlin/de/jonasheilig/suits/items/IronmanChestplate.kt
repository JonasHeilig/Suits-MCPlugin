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

class IronmanChestplate {

    fun create(): ItemStack {
        val chestplate = ItemStack(Material.LEATHER_CHESTPLATE)
        val meta: LeatherArmorMeta = chestplate.itemMeta as LeatherArmorMeta

        meta.setDisplayName("${ChatColor.GOLD}Ironman Chestplate")
        meta.isUnbreakable = true
        meta.setColor(Color.RED)

        val lore = listOf(
            "${ChatColor.DARK_BLUE}Only Ironman Chestplate:",
            "${ChatColor.BLUE}Absorption",
            "${ChatColor.GOLD}Set Bonus: Enhanced Abilities"
        )
        meta.lore = lore
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE)

        val key = NamespacedKey.fromString("ironman_chestplate")!!
        meta.persistentDataContainer.set(key, PersistentDataType.STRING, UUID.randomUUID().toString())

        if (meta is ArmorMeta) {
            val pattern = TrimPattern.WILD
            val material = TrimMaterial.GOLD
            val trim = ArmorTrim(material, pattern)
            meta.trim = trim
        }

        chestplate.itemMeta = meta
        return chestplate
    }
}
