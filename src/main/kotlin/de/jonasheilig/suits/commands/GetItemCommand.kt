package de.jonasheilig.suits.commands

import de.jonasheilig.suits.items.*
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class GetItemCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) {
            sender.sendMessage("Only players can use this command.")
            return true
        }

        if (!sender.hasPermission("suitsplugin.getitem")) {
            sender.sendMessage("${ChatColor.RED}You do not have permission to use this command.")
            return true
        }

        if (args.size != 2) {
            sender.sendMessage("${ChatColor.RED}Usage: /get <playername> <item>")
            return true
        }

        val targetPlayerName = args[0]
        val itemType = args[1]

        val targetPlayer = Bukkit.getPlayer(targetPlayerName)
        if (targetPlayer == null) {
            sender.sendMessage("Player not found.")
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
            else -> {
                sender.sendMessage("${ChatColor.RED}Invalid item type.")
                return true
            }
        }

        targetPlayer.inventory.addItem(itemStack)
        sender.sendMessage("${ChatColor.GREEN}Gave ${itemType} to ${targetPlayerName}.")
        return true
    }
}
