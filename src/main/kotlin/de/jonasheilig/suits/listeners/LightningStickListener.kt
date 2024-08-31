package de.jonasheilig.suits.listeners

import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.persistence.PersistentDataType
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.scheduler.BukkitTask

class LightningStickListener(private val plugin: JavaPlugin) : Listener {

    private val cooldowns: MutableMap<Player, BukkitTask> = HashMap()

    @EventHandler
    fun onPlayerInteract(event: PlayerInteractEvent) {
        val player = event.player
        val item = player.inventory.itemInMainHand

        if (item.type == Material.BLAZE_ROD && item.hasItemMeta()) {
            val meta = item.itemMeta!!
            val key = NamespacedKey.fromString("lightning_stick")!!

            if (meta.persistentDataContainer.has(key, PersistentDataType.STRING)) {
                event.isCancelled = true

                if (cooldowns.containsKey(player)) {
                    return
                }

                val targetBlock = player.getTargetBlockExact(100)
                if (targetBlock != null) {
                    player.world.strikeLightning(targetBlock.location)
                } else {
                    val targetEntity = player.getTargetEntity(100)
                    if (targetEntity is LivingEntity) {
                        player.world.strikeLightning(targetEntity.location)
                    }
                }

                startCooldown(player)
            }
        }
    }

    private fun startCooldown(player: Player) {
        val cooldownTask = object : BukkitRunnable() {
            override fun run() {
                cooldowns.remove(player)
            }
        }.runTaskLater(plugin, 200)

        cooldowns[player] = cooldownTask
    }
}
