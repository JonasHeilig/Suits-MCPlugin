package de.jonasheilig.suits.listeners

import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerItemHeldEvent
import org.bukkit.event.player.PlayerSwapHandItemsEvent
import org.bukkit.persistence.PersistentDataType
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.inventory.ItemStack

class TarnstoneListener(private val plugin: JavaPlugin) : Listener {

    private val invisibilityTasks: MutableMap<Player, BukkitRunnable> = HashMap()

    @EventHandler
    fun onPlayerItemHeld(event: PlayerItemHeldEvent) {
        val player = event.player
        val newItem = player.inventory.getItem(event.newSlot)

        checkAndApplyInvisibility(player, newItem)
    }

    @EventHandler
    fun onPlayerSwapHandItems(event: PlayerSwapHandItemsEvent) {
        val player = event.player
        val newItem = event.offHandItem

        checkAndApplyInvisibility(player, newItem)
    }

    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        val player = event.player
        val itemInMainHand = player.inventory.itemInMainHand
        val itemInOffHand = player.inventory.itemInOffHand

        if (hasTarnstone(itemInMainHand) || hasTarnstone(itemInOffHand)) {
            applyInvisibility(player)
        }
    }

    @EventHandler
    fun onPlayerQuit(event: PlayerQuitEvent) {
        val player = event.player
        invisibilityTasks.remove(player)?.cancel()
    }

    private fun checkAndApplyInvisibility(player: Player, item: ItemStack?) {
        if (hasTarnstone(player.inventory.itemInMainHand) || hasTarnstone(player.inventory.itemInOffHand)) {
            if (!player.hasPotionEffect(org.bukkit.potion.PotionEffectType.INVISIBILITY)) {
                applyInvisibility(player)
            }
        } else {
            removeInvisibility(player)
        }
    }

    private fun hasTarnstone(item: ItemStack?): Boolean {
        if (item == null || item.type != Material.STONE || !item.hasItemMeta()) return false
        val meta = item.itemMeta!!
        val key = NamespacedKey.fromString("tarnstone")!!
        return meta.persistentDataContainer.has(key, PersistentDataType.STRING)
    }

    private fun applyInvisibility(player: Player) {
        if (invisibilityTasks.containsKey(player)) return

        player.addPotionEffect(org.bukkit.potion.PotionEffect(org.bukkit.potion.PotionEffectType.INVISIBILITY, Int.MAX_VALUE, 1, false, false, false))

        val task = object : BukkitRunnable() {
            override fun run() {
                if (!hasTarnstone(player.inventory.itemInMainHand) && !hasTarnstone(player.inventory.itemInOffHand)) {
                    removeInvisibility(player)
                    cancel()
                }
            }
        }.runTaskTimer(plugin, 0, 10)

    }

    private fun removeInvisibility(player: Player) {
        invisibilityTasks.remove(player)?.cancel()
        player.removePotionEffect(org.bukkit.potion.PotionEffectType.INVISIBILITY)
    }
}
