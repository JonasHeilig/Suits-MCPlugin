package de.jonasheilig.suits.listeners

import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerItemHeldEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.bukkit.persistence.PersistentDataType

class MinersTearListener : Listener {

    @EventHandler
    fun onPlayerItemHeld(event: PlayerItemHeldEvent) {
        val player = event.player
        val item = player.inventory.itemInMainHand

        if (item.type == Material.GHAST_TEAR && item.hasItemMeta()) {
            val meta = item.itemMeta!!
            val key = NamespacedKey.fromString("miners_tear")!!

            if (meta.persistentDataContainer.has(key, PersistentDataType.STRING)) {
                applyHasteEffect(player)
            }
        }
    }

    private fun applyHasteEffect(player: Player) {
        player.addPotionEffect(PotionEffect(PotionEffectType.HASTE, 20 * 60, 1, true, false))
    }
}
