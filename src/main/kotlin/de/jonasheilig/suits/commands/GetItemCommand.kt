package de.jonasheilig.suits.commands

import de.jonasheilig.suits.items.*
import org.bukkit.Bukkit
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class GetItemCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) {
            sender.sendMessage(Component.text("Only players can use this command.").color(NamedTextColor.RED))
            return true
        }

        if (!sender.hasPermission("suitsplugin.getitem")) {
            sender.sendMessage(Component.text("You do not have permission to use this command.").color(NamedTextColor.RED))
            return true
        }

        if (args.size != 2) {
            sender.sendMessage(Component.text("Usage: /get <playername> <item>").color(NamedTextColor.RED))
            return true
        }

        val targetPlayerName = args[0]
        val itemType = args[1]

        val targetPlayer = Bukkit.getPlayer(targetPlayerName)
        if (targetPlayer == null) {
            sender.sendMessage(Component.text("Player not found.").color(NamedTextColor.RED))
            return true
        }

        val itemStack = when (itemType.lowercase()) {
            "ironman_helmet" -> IronmanHelmet().create()
            "ironman_chestplate" -> IronmanChestplate().create()
            "ironman_leggings" -> IronmanLeggings().create()
            "ironman_boots" -> IronmanBoots().create()
            "ark_reactor" -> ARKReaktor().create()
            "jarvis" -> JARVIS().create()
            "exo_stick" -> EXO().create()
            "jet_engine_core" -> JetEngineCore().create()
            "jet_engine" -> JetEngine().create()
            "repulso_core" -> RepulsoCore().create()
            "repulso_tool" -> RepulsoTool().create()
            "laser_core" -> LaserCore().create()
            "laser_staff" -> LaserStaff().create()
            "energy_snowball" -> EnergySnowball().create()
            "super_carrot" -> SuperCarrot().create()
            "energy_sword" -> EnergySword().create()
            "firestick" -> Firestick().create()
            "time_accelerator" -> TimeAccelerator().create()
            "tarnstone" -> Tarnstone().create()
            "lightning_stick" -> LightningStick().create()
            "phoenix_feather" -> PhoenixFeather().create()
            "levitation_orb" -> LevitationOrb().create()
            "impulse_bomb" -> ImpulseBomb().create()
            "bedport" -> Bedport().create()
            else -> {
                sender.sendMessage(Component.text("Invalid item type.").color(NamedTextColor.RED))
                return true
            }
        }

        targetPlayer.inventory.addItem(itemStack)
        sender.sendMessage(Component.text("Gave $itemType to $targetPlayerName.").color(NamedTextColor.GREEN))
        return true
    }
}