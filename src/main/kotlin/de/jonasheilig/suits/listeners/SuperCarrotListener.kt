package de.jonasheilig.suits.listeners

import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerItemConsumeEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.persistence.PersistentDataType
import org.bukkit.NamespacedKey

class SuperCarrotListener(private val plugin: JavaPlugin) : Listener {

    @EventHandler
    fun onPlayerConsume(event: PlayerItemConsumeEvent) {
        val player = event.player
        val item = event.item

        if (item.type == Material.GOLDEN_CARROT && item.hasItemMeta()) {
            val meta = item.itemMeta!!
            val key = NamespacedKey.fromString("super_carrot")!!
            if (meta.persistentDataContainer.has(key, PersistentDataType.STRING)) {
                player.addPotionEffect(PotionEffect(PotionEffectType.REGENERATION, 20 * 20, 1))
                player.addPotionEffect(PotionEffect(PotionEffectType.STRENGTH, 20 * 20, 1))
                player.addPotionEffect(PotionEffect(PotionEffectType.RESISTANCE, 60 * 20, 1))
                player.addPotionEffect(PotionEffect(PotionEffectType.GLOWING, 25 * 20, 0))
            }
        }
    }
}
