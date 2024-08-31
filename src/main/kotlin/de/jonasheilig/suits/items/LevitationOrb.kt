package de.jonasheilig.suits.items

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.persistence.PersistentDataType

class LevitationOrb {

    fun create(): ItemStack {
        val orb = ItemStack(Material.ENDER_EYE)
        val meta: ItemMeta = orb.itemMeta!!

        meta.displayName(Component.text("Levitation Orb", NamedTextColor.BLUE))

        meta.isUnbreakable = true
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE)
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES)

        val lore = listOf(
            Component.text("Hold to levitate,", NamedTextColor.GOLD),
            Component.text("Release to gain Feather Falling for 30 seconds.", NamedTextColor.GREEN)
        )
        meta.lore(lore)

        val key = NamespacedKey.fromString("levitation_orb")!!
        meta.persistentDataContainer.set(key, PersistentDataType.STRING, "levitation_orb")

        meta.setMaxStackSize(1)

        orb.itemMeta = meta

        return orb
    }
}
