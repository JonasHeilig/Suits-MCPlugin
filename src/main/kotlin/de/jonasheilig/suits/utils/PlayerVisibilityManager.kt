package de.jonasheilig.suits.utils

import org.bukkit.Bukkit
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType
import org.bukkit.NamespacedKey
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.plugin.java.JavaPlugin

object PlayerVisibilityManager {

    private val hologramKey = NamespacedKey("suitsplugin", "hologram")

    fun showPlayerHolograms(viewer: Player, plugin: JavaPlugin) {
        Bukkit.getOnlinePlayers().forEach { target ->
            if (target != viewer) {
                val location = target.location.add(0.0, 2.5, 0.0)

                val armorStand = viewer.world.spawn(location, ArmorStand::class.java)
                armorStand.isVisible = false
                armorStand.isMarker = true
                armorStand.isCustomNameVisible = true
                armorStand.customName = buildHologramText(target)
                armorStand.setGravity(false)
                armorStand.setSmall(true)

                armorStand.persistentDataContainer.set(hologramKey, PersistentDataType.STRING, "true")

                object : BukkitRunnable() {
                    override fun run() {
                        if (!isWearingIronmanHelmet(viewer)) {
                            armorStand.remove()
                            cancel()
                        } else {
                            armorStand.teleport(target.location.add(0.0, 2.5, 0.0))
                            armorStand.customName = buildHologramText(target)
                        }
                    }
                }.runTaskTimer(plugin, 0L, 20L)
            }
        }
    }

    fun hidePlayerHolograms(viewer: Player) {
        viewer.world.entities.filterIsInstance<ArmorStand>().forEach { armorStand ->
            if (armorStand.persistentDataContainer.has(hologramKey, PersistentDataType.STRING)) {
                armorStand.remove()
            }
        }
    }

    private fun isWearingIronmanHelmet(player: Player): Boolean {
        return hasCustomTag(player.inventory.helmet, "ironman_helmet")
    }

    private fun buildHologramText(player: Player): String {
        val health = "Health: ${player.health}/${player.maxHealth}"
        val armor = "Armor: ${player.inventory.armorContents.filterNotNull().joinToString(", ") { it.type.name }}"
        val effects = player.activePotionEffects.joinToString(", ") {
            "${it.type.name} (${it.duration / 20}s)"
        }

        return "${player.name}\n$health\n$armor\n$effects"
    }

    private fun hasCustomTag(item: ItemStack?, tag: String): Boolean {
        val meta = item?.itemMeta
        val key = NamespacedKey.fromString(tag) ?: return false
        return meta?.persistentDataContainer?.has(key, PersistentDataType.STRING) == true
    }
}
