package de.jonasheilig.suits.listeners

import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.entity.Fireball
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.persistence.PersistentDataType
import org.bukkit.plugin.java.JavaPlugin

class FirestickListener(private val plugin: JavaPlugin) : Listener {

    @EventHandler
    fun onPlayerInteract(event: PlayerInteractEvent) {
        val player = event.player
        val item = player.inventory.itemInMainHand

        if (event.action == Action.RIGHT_CLICK_AIR || event.action == Action.RIGHT_CLICK_BLOCK) {
            if (item.type == Material.STICK && item.hasItemMeta()) {
                val meta = item.itemMeta!!
                val key = NamespacedKey.fromString("firestick")!!

                if (meta.persistentDataContainer.has(key, PersistentDataType.STRING)) {
                    event.isCancelled = true

                    shootFireball(player)
                }
            }
        }
    }

    private fun shootFireball(player: Player) {
        val direction = player.eyeLocation.direction
        val fireball = player.world.spawn(player.eyeLocation.add(direction.multiply(2.0)), Fireball::class.java)
        fireball.direction = direction
        fireball.velocity = direction.multiply(1.5)
    }
}
