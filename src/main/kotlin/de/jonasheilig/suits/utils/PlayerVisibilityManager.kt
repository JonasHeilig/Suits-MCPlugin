package de.jonasheilig.suits.utils

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
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
    private val ironmanHelmetTagKey = NamespacedKey("suitsplugin", "ironman_helmet")

    fun showPlayerHolograms(viewer: Player, plugin: JavaPlugin) {
        Bukkit.getOnlinePlayers().forEach { target ->
            if (target != viewer) {
                val location = target.location.add(0.0, 2.5, 0.0)

                val armorStand = viewer.world.spawn(location, ArmorStand::class.java)
                armorStand.isVisible = false
                armorStand.isMarker = true
                armorStand.isCustomNameVisible = true
                armorStand.customName(Component.text(buildHologramText(target)))
                armorStand.setGravity(false)
                armorStand.isSmall = true

                armorStand.persistentDataContainer.set(hologramKey, PersistentDataType.STRING, "true")

                object : BukkitRunnable() {
                    override fun run() {
                        if (!isWearingIronmanHelmet(viewer)) {
                            armorStand.remove()
                            cancel()
                        } else {
                            armorStand.teleport(target.location.add(0.0, 2.5, 0.0))
                            armorStand.customName(Component.text(buildHologramText(target)))
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
        return hasIronmanHelmetTag(player.inventory.helmet)
    }

    private fun buildHologramText(player: Player): String {
        val playerName = PlainTextComponentSerializer.plainText().serialize(player.name())
        val health = "Health: ${player.health}/${player.healthScale}"
        val armor = "Armor: ${player.inventory.armorContents.filterNotNull().joinToString(", ") { it.type.name }}"
        val effects = player.activePotionEffects.joinToString(", ") {
            "${it.type.name} (${it.duration / 20}s)"
        }

        return "$playerName\n$health\n$armor\n$effects"
    }

    private fun hasIronmanHelmetTag(item: ItemStack?): Boolean {
        val meta = item?.itemMeta
        return meta?.persistentDataContainer?.has(ironmanHelmetTagKey, PersistentDataType.STRING) == true
    }
}
