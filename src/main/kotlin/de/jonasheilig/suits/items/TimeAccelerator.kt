package de.jonasheilig.suits.items

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.persistence.PersistentDataType
import java.util.UUID

class TimeAccelerator {

    fun create(): ItemStack {
        val timeAccelerator = ItemStack(Material.CLOCK)
        val meta: ItemMeta = timeAccelerator.itemMeta!!

        meta.displayName(Component.text("Time Accelerator", NamedTextColor.AQUA))

        meta.isUnbreakable = true
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE)
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES)

        val lore = listOf(
            Component.text("Accelerates time around the block you right-click", NamedTextColor.AQUA),
            Component.text("for 40 seconds.", NamedTextColor.GREEN),
            Component.text("", NamedTextColor.RED),
            Component.text("-----------------------------", NamedTextColor.GRAY),
            Component.text("", NamedTextColor.RED),
            Component.text("5 minute cooldown.", NamedTextColor.RED)
        )
        meta.lore(lore)

        val key = NamespacedKey.fromString("time_accelerator")!!
        meta.persistentDataContainer.set(key, PersistentDataType.STRING, UUID.randomUUID().toString())

        timeAccelerator.itemMeta = meta

        return timeAccelerator
    }
}
