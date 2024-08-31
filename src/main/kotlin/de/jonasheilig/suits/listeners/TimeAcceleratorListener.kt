package de.jonasheilig.suits.listeners

import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.block.Block
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.persistence.PersistentDataType
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.scheduler.BukkitTask
import java.util.HashMap

class TimeAcceleratorListener(private val plugin: JavaPlugin) : Listener {

    private val cooldowns: MutableMap<Player, BukkitTask> = HashMap()

    @EventHandler
    fun onPlayerInteract(event: PlayerInteractEvent) {
        val player = event.player
        val item = player.inventory.itemInMainHand

        if (event.action == Action.RIGHT_CLICK_BLOCK && item.type == Material.CLOCK && item.hasItemMeta()) {
            val meta = item.itemMeta!!
            val key = NamespacedKey.fromString("time_accelerator")!!

            if (meta.persistentDataContainer.has(key, PersistentDataType.STRING)) {
                event.isCancelled = true

                if (cooldowns.containsKey(player)) {
                    player.sendMessage("You must wait before using this item again.")
                    return
                }

                val block = event.clickedBlock!!
                accelerateTime(player, block)
                startCooldown(player)
            }
        }
    }

    private fun accelerateTime(player: Player, block: Block) {
        player.sendMessage("Time acceleration activated around ${block.type.name} for 40 seconds!")

        object : BukkitRunnable() {
            var timeElapsed = 0

            override fun run() {
                if (timeElapsed >= 40) {
                    cancel()
                    return
                }

                accelerateCropsAround(block)

                timeElapsed++
            }
        }.runTaskTimer(plugin, 0, 20)
    }

    private fun accelerateCropsAround(block: Block) {
        val world = block.world
        val radius = 5

        for (x in -radius..radius) {
            for (y in -radius..radius) {
                for (z in -radius..radius) {
                    val nearbyBlock = block.location.clone().add(x.toDouble(), y.toDouble(), z.toDouble()).block
                    if (isCrop(nearbyBlock)) {
                        accelerateCropGrowth(nearbyBlock)
                    }
                }
            }
        }
    }

    private fun isCrop(block: Block): Boolean {
        return when (block.type) {
            Material.WHEAT, Material.CARROTS, Material.POTATOES, Material.BEETROOTS -> true
            else -> false
        }
    }

    private fun accelerateCropGrowth(block: Block) {
        when (block.type) {
            Material.WHEAT -> {
                val age = block.getState().getAge()
                if (age < 7) {
                    block.getState().setAge(age + 1)
                }
            }
            Material.CARROTS -> {
                val age = block.getState().getAge()
                if (age < 7) {
                    block.getState().setAge(age + 1)
                }
            }
            Material.POTATOES -> {
                val age = block.getState().setAge()
                if (age < 7) {
                    block.getState().setAge(age + 1)
                }
            }
            Material.BEETROOTS -> {
                val age = block.getState().getAge()
                if (age < 3) {
                    block.getState().setAge(age + 1)
                }
            }
        }
    }

    private fun startCooldown(player: Player) {
        val cooldownTask = object : BukkitRunnable() {
            override fun run() {
                cooldowns.remove(player)
                player.sendMessage("You can use the Time Accelerator again.")
            }
        }.runTaskLater(plugin, 6000) // 6000 ticks = 5 minutes

        cooldowns[player] = cooldownTask
        player.sendMessage("You must wait 5 minutes before using this again.")
    }
}
