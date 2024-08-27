package de.jonasheilig.suits.items

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.persistence.PersistentDataType
import java.util.*

class EnergySword {
    fun create(): ItemStack {
        val sword = ItemStack(Material.DIAMOND_SWORD)
        val meta: ItemMeta = sword.itemMeta

        meta.displayName(Component.text("Energy Sword", NamedTextColor.AQUA))

        val lore = listOf(
            Component.text("A sword with energy.", NamedTextColor.GOLD),
            Component.text("Applies negative effects to enemies on hit.", NamedTextColor.GOLD)
        )
        meta.lore(lore)

        meta.setEnchantmentGlintOverride(true)
        meta.isUnbreakable = true

        meta.addEnchant(Enchantment.SHARPNESS, 3, true)
        meta.addEnchant(Enchantment.KNOCKBACK, 2, true)

        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE)
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES)
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS)

        val key = NamespacedKey.fromString("energy_sword")!!
        meta.persistentDataContainer.set(key, PersistentDataType.STRING, UUID.randomUUID().toString())

        sword.itemMeta = meta

        return sword
    }
}
