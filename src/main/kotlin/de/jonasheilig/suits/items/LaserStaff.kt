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

class LaserStaff {
    fun create(): ItemStack {
        val staff = ItemStack(Material.BLAZE_ROD)
        val meta: ItemMeta = staff.itemMeta

        meta.displayName(Component.text("Laser Staff", NamedTextColor.RED))

        val lore = listOf(
            Component.text("Shoots a powerful laser beam.", NamedTextColor.GRAY),
            Component.text("Charge time: 3 seconds.", NamedTextColor.DARK_RED)
        )
        meta.lore(lore)

        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS)
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES)

        val key = NamespacedKey.fromString("laser_staff")!!
        meta.persistentDataContainer.set(key, PersistentDataType.STRING, UUID.randomUUID().toString())

        staff.itemMeta = meta

        return staff
    }
}
