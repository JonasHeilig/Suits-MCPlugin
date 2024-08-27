package de.jonasheilig.suits.listeners

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.persistence.PersistentDataType
import org.bukkit.NamespacedKey
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player

class EnergySwordListener(private val plugin: JavaPlugin) : Listener {

    @EventHandler
    fun onEntityDamageByEntity(event: EntityDamageByEntityEvent) {
        if (event.damager is Player && event.entity is LivingEntity) {
            val player = event.damager as Player
            val entity = event.entity as LivingEntity
            val item = player.inventory.itemInMainHand

            if (item.hasItemMeta()) {
                val meta = item.itemMeta!!
                val key = NamespacedKey.fromString("energy_sword")!!
                if (meta.persistentDataContainer.has(key, PersistentDataType.STRING)) {
                    entity.addPotionEffect(PotionEffect(PotionEffectType.POISON, 100, 1))
                    entity.addPotionEffect(PotionEffect(PotionEffectType.SLOWNESS, 100, 1))
                    entity.addPotionEffect(PotionEffect(PotionEffectType.WEAKNESS, 100, 1))
                }
            }
        }
    }
}
