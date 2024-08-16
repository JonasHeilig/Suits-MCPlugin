package de.jonasheilig.suits.listeners

import org.bukkit.Particle
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.NamespacedKey
import org.bukkit.event.block.Action
import org.bukkit.persistence.PersistentDataType

class LaserStaffListener(private val plugin: JavaPlugin) : Listener {

    @EventHandler
    fun onPlayerInteract(event: PlayerInteractEvent) {
        val player = event.player
        val action = event.action

        if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
            val item = player.inventory.itemInMainHand
            val key = NamespacedKey(plugin, "laser_staff")
            if (item.itemMeta?.persistentDataContainer?.has(key, PersistentDataType.STRING) == true) {
                chargeLaser(player)
            }
        }
    }

    private fun chargeLaser(player: Player) {
        object : BukkitRunnable() {
            override fun run() {
                player.world.spawnParticle(Particle.CRIT, player.eyeLocation, 10, 0.2, 0.2, 0.2, 0.1)

                object : BukkitRunnable() {
                    override fun run() {
                        shootLaser(player)
                    }
                }.runTaskLater(plugin, 60L)
            }
        }.runTaskTimer(plugin, 0, 1)
    }

    private fun shootLaser(player: Player) {
        val direction = player.eyeLocation.direction
        val location = player.eyeLocation.add(direction.multiply(1.5))
        val maxDistance = 50.0

        object : BukkitRunnable() {
            var distanceTraveled = 0.0

            override fun run() {
                if (distanceTraveled >= maxDistance) {
                    cancel()
                    return
                }

                distanceTraveled += 1.0
                val newLocation = location.add(direction)
                player.world.spawnParticle(Particle.ELECTRIC_SPARK, newLocation, 1)

                val entities = player.world.getNearbyEntities(newLocation, 0.5, 0.5, 0.5)
                for (entity in entities) {
                    if (entity is LivingEntity && entity !== player) {
                        entity.damage(20.0, player) // 20 damage from the laser
                        cancel()
                        return
                    }
                }

                location.subtract(direction)
            }
        }.runTaskTimer(plugin, 0, 1)
    }
}
