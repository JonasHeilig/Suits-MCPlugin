package de.jonasheilig.suits.listeners

import org.bukkit.NamespacedKey
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerItemHeldEvent
import org.bukkit.persistence.PersistentDataType
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class LevitationOrbListener(private val plugin: JavaPlugin) : Listener {

    @EventHandler
    fun onItemHeld(event: PlayerItemHeldEvent) {
        val player = event.player
        val newItem = player.inventory.getItem(event.newSlot)
        val oldItem = player.inventory.getItem(event.previousSlot)

        if (newItem?.itemMeta?.persistentDataContainer?.has(NamespacedKey.fromString("levitation_orb")!!, PersistentDataType.STRING) == true) {
            player.addPotionEffect(PotionEffect(PotionEffectType.LEVITATION, Int.MAX_VALUE, 1, false, false))
        }

        if (oldItem?.itemMeta?.persistentDataContainer?.has(NamespacedKey.fromString("levitation_orb")!!, PersistentDataType.STRING) == true) {
            player.removePotionEffect(PotionEffectType.LEVITATION)
            player.addPotionEffect(PotionEffect(PotionEffectType.SLOW_FALLING, 600, 0, false, false))
        }
    }
}
