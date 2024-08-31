package de.jonasheilig.suits.listeners

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.persistence.PersistentDataType
import org.bukkit.scheduler.BukkitRunnable

class BedportListener(private val plugin: JavaPlugin) : Listener {

    @EventHandler
    fun onPlayerInteract(event: PlayerInteractEvent) {
        val player = event.player
        val item = player.inventory.itemInMainHand

        if (event.action == Action.RIGHT_CLICK_BLOCK && item.type == Material.EMERALD && item.hasItemMeta()) {
            val meta = item.itemMeta!!
            val key = NamespacedKey.fromString("bedport")!!

            if (meta.persistentDataContainer.has(key, PersistentDataType.STRING)) {
                event.isCancelled = true
                teleportToBed(player)
            }
        }
    }

    private fun teleportToBed(player: Player) {
        val bedLocation = player.bedSpawnLocation

        if (bedLocation != null) {
            object : BukkitRunnable() {
                override fun run() {
                    player.teleport(bedLocation)
                }
            }.runTask(plugin)
        }

    }
}