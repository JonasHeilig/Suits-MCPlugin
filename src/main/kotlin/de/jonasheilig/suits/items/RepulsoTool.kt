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

class RepulsoTool {
    fun create(): ItemStack {
        val tool = ItemStack(Material.STICK)
        val meta: ItemMeta = tool.itemMeta

        meta.displayName(Component.text("Repulso Tool", NamedTextColor.RED))

        val lore = listOf(
            Component.text("Shoots powerful energy blasts.", NamedTextColor.GOLD),
            Component.text("Deals 15 damage and has a 7-second cooldown.", NamedTextColor.DARK_RED)
        )
        meta.lore(lore)

        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE)
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES)

        val key = NamespacedKey.fromString("repulso_tool")!!
        meta.persistentDataContainer.set(key, PersistentDataType.STRING, UUID.randomUUID().toString())

        tool.itemMeta = meta

        return tool
    }
}
