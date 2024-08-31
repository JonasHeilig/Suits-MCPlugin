package de.jonasheilig.suits.listeners

import org.bukkit.Location
import org.bukkit.NamespacedKey
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.persistence.PersistentDataType
import org.bukkit.plugin.java.JavaPlugin
import kotlin.random.Random

class PhoenixFeatherListener(private val plugin: JavaPlugin) : Listener {

    @EventHandler
    fun onPlayerDeath(event: PlayerDeathEvent) {
        val player = event.entity

        val feather = player.inventory.contents.firstOrNull {
            it?.itemMeta?.persistentDataContainer?.has(NamespacedKey.fromString("phoenix_feather")!!, PersistentDataType.STRING) == true
        }

        if (feather != null) {
            event.isCancelled = true
            player.health = player.maxHealth
            val safeLocation = findSafeLocation(player.location, 40)
            player.teleport(safeLocation ?: player.world.spawnLocation)
            player.inventory.remove(feather)
        }
    }

    private fun findSafeLocation(center: Location, radius: Int): Location? {
        val world = center.world ?: return null

        for (i in 0 until 100) {
            val randomX = center.x + Random.nextInt(-radius, radius)
            val randomZ = center.z + Random.nextInt(-radius, radius)
            val highestY = world.getHighestBlockYAt(randomX.toInt(), randomZ.toInt())
            val potentialLocation = Location(world, randomX, highestY.toDouble(), randomZ)

            if (isLocationSafe(potentialLocation)) {
                return potentialLocation
            }
        }

        return null
    }

    private fun isLocationSafe(location: Location): Boolean {
        val block = location.block
        val below = block.getRelative(org.bukkit.block.BlockFace.DOWN)

        return block.type.isAir && below.type.isSolid
    }
}
