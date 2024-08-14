package de.jonasheilig.suits.listeners

import de.jonasheilig.suits.utils.PlayerVisibilityManager
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType
import org.bukkit.NamespacedKey
import org.bukkit.plugin.java.JavaPlugin

class IronSuitListener(private val plugin: JavaPlugin) : Listener {

    @EventHandler
    fun onPlayerMove(event: PlayerMoveEvent) {
        val player = event.player

        if (isWearingIronmanHelmet(player) && isWearingIronmanChestplate(player)) {
            PlayerVisibilityManager.showPlayerHolograms(player, plugin)
            player.addPotionEffect(PotionEffect(PotionEffectType.NIGHT_VISION, 200, 1, true, false))
        } else {
            PlayerVisibilityManager.hidePlayerHolograms(player)
            player.removePotionEffect(PotionEffectType.NIGHT_VISION)
        }

        if (isWearingIronmanChestplate(player)){
            player.addPotionEffect(PotionEffect(PotionEffectType.ABSORPTION, 200, 3, true, false))
        } else {
            player.removePotionEffect(PotionEffectType.ABSORPTION)
        }

        if (isWearingIronmanLeggings(player)) {
            player.addPotionEffect(PotionEffect(PotionEffectType.SPEED, 200, 1, true, false))
        } else {
            player.removePotionEffect(PotionEffectType.SPEED)
        }

        if (isWearingIronmanBoots(player)) {
            player.addPotionEffect(PotionEffect(PotionEffectType.JUMP_BOOST, 200, 1, true, false))
            player.allowFlight = true
        } else {
            player.removePotionEffect(PotionEffectType.JUMP_BOOST)
            player.allowFlight = false
        }

        if (isWearingFullIronmanSuit(player)) {
            player.addPotionEffect(PotionEffect(PotionEffectType.RESISTANCE, 200, 200, true, false))
            player.addPotionEffect(PotionEffect(PotionEffectType.FIRE_RESISTANCE, 200, 200, true, false))
            player.addPotionEffect(PotionEffect(PotionEffectType.HASTE, 200, 3, true, false))
            player.addPotionEffect(PotionEffect(PotionEffectType.STRENGTH, 200, 2, true, false))
            player.addPotionEffect(PotionEffect(PotionEffectType.SATURATION, 200, 2, true, false))
            player.addPotionEffect(PotionEffect(PotionEffectType.WATER_BREATHING, 200, 2, true, false))
        } else {
            player.removePotionEffect(PotionEffectType.RESISTANCE)
            player.removePotionEffect(PotionEffectType.FIRE_RESISTANCE)
            player.removePotionEffect(PotionEffectType.HASTE)
            player.removePotionEffect(PotionEffectType.STRENGTH)
            player.removePotionEffect(PotionEffectType.SATURATION)
            player.removePotionEffect(PotionEffectType.WATER_BREATHING)
        }
    }

    private fun isWearingIronmanHelmet(player: org.bukkit.entity.Player): Boolean {
        return hasCustomTag(player.inventory.helmet, "ironman_helmet")
    }

    private fun isWearingIronmanChestplate(player: org.bukkit.entity.Player): Boolean {
        return hasCustomTag(player.inventory.chestplate, "ironman_chestplate")
    }

    private fun isWearingIronmanLeggings(player: org.bukkit.entity.Player): Boolean {
        return hasCustomTag(player.inventory.leggings, "ironman_leggings")
    }

    private fun isWearingIronmanBoots(player: org.bukkit.entity.Player): Boolean {
        return hasCustomTag(player.inventory.boots, "ironman_boots")
    }

    private fun isWearingFullIronmanSuit(player: org.bukkit.entity.Player): Boolean {
        return isWearingIronmanHelmet(player) &&
                isWearingIronmanChestplate(player) &&
                isWearingIronmanLeggings(player) &&
                isWearingIronmanBoots(player)
    }

    private fun hasCustomTag(item: ItemStack?, tag: String): Boolean {
        val meta = item?.itemMeta
        val key = NamespacedKey.fromString(tag) ?: return false
        return meta?.persistentDataContainer?.has(key, PersistentDataType.STRING) == true
    }
}
