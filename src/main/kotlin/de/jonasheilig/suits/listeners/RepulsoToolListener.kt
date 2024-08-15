package de.jonasheilig.suits.listeners

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Material
import org.bukkit.Particle
import org.bukkit.Color
import org.bukkit.NamespacedKey
import org.bukkit.entity.Entity
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.persistence.PersistentDataType
import java.util.UUID

class RepulsoToolListener(private val plugin: JavaPlugin) : Listener {
    private val cooldowns = mutableMapOf<UUID, Long>()

    @EventHandler
    fun onPlayerInteract(event: PlayerInteractEvent) {
        val player = event.player
        val action = event.action
        if (action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK) {
            val item = player.inventory.itemInMainHand
            if (item.type == Material.STICK && item.hasItemMeta()) {
                val meta = item.itemMeta!!
                val key = NamespacedKey.fromString("repulso_tool")!!
                if (meta.persistentDataContainer.has(key, PersistentDataType.STRING)) {
                    val now = System.currentTimeMillis()
                    val lastUsed = cooldowns[player.uniqueId] ?: 0
                    if (now - lastUsed < 7000) { // 7 seconds cooldown
                        player.sendMessage("Cooldown not finished!")
                        return
                    }
                    cooldowns[player.uniqueId] = now

                    shootRepulsoBlast(player)
                }
            }
        }
    }

    private fun shootRepulsoBlast(player: Player) {
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
                player.world.spawnParticle(Particle.ELECTRIC_SPARK, newLocation, 1, Particle.DustOptions(Color.RED, 1.0f))

                val entities = player.world.getNearbyEntities(newLocation, 0.5, 0.5, 0.5)
                for (entity in entities) {
                    if (entity is LivingEntity && entity !== player) {
                        entity.damage(15.0, player)
                        cancel()
                        return
                    }
                }

                location.subtract(direction)
            }
        }.runTaskTimer(plugin, 0, 1)
    }
}
