package de.jonasheilig.suits.listeners

import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.Particle
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.persistence.PersistentDataType
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable

class EnergySnowballListener(private val plugin: JavaPlugin) : Listener {
    @EventHandler
    fun onPlayerInteract(event: PlayerInteractEvent) {
        val player = event.player
        val item = player.inventory.itemInMainHand
        if (item.type == Material.SNOWBALL && item.hasItemMeta()) {
            val meta = item.itemMeta!!
            val key = NamespacedKey.fromString("energy_snowball")!!
            if (meta.persistentDataContainer.has(key, PersistentDataType.STRING)) {
                event.isCancelled = true
                throwEnergySnowball(player)
            }
        }
    }

    private fun throwEnergySnowball(player: Player) {
        val direction = player.eyeLocation.direction
        val location = player.eyeLocation.add(direction.multiply(1.5))
        val maxDistance = 30.0

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
                player.world.spawnParticle(Particle.WITCH, newLocation, 1)

                val entities = player.world.getNearbyEntities(newLocation, 1.0, 1.0, 1.0)
                for (entity in entities) {
                    if (entity is LivingEntity && entity !== player) {
                        entity.damage(10.0, player)
                        entity.addPotionEffect(org.bukkit.potion.PotionEffect(org.bukkit.potion.PotionEffectType.LEVITATION, 20 * 2, 1))
                        cancel()
                        return
                    }
                }

                location.subtract(direction)
            }
        }.runTaskTimer(plugin, 0, 1)
    }
}
