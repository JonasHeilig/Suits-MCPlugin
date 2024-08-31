package de.jonasheilig.suits.listeners

import org.bukkit.NamespacedKey
import org.bukkit.entity.EntityType
import org.bukkit.entity.LivingEntity
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.persistence.PersistentDataType
import org.bukkit.plugin.java.JavaPlugin

class ImpulseBombListener(private val plugin: JavaPlugin) : Listener {

    @EventHandler
    fun onPlayerInteract(event: PlayerInteractEvent) {
        val player = event.player
        val item = player.inventory.itemInMainHand

        if (item.itemMeta?.persistentDataContainer?.has(NamespacedKey.fromString("impulse_bomb")!!, PersistentDataType.STRING) == true) {
            event.isCancelled = true

            val nearbyEntities = player.world.getNearbyEntities(player.location, 5.0, 5.0, 5.0)
            nearbyEntities.forEach { entity ->
                if (entity is LivingEntity && entity.type != EntityType.PLAYER) {
                    entity.damage(entity.health)
                }
            }

            item.amount = 0
        }
    }
}
