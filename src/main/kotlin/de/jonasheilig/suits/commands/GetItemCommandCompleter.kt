package de.jonasheilig.suits.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player

class GetItemCommandCompleter : TabCompleter {

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        alias: String,
        args: Array<out String>
    ): List<String>? {
        if (sender !is Player) {
            return null
        }

        if (args.size == 1) {
            return sender.server.onlinePlayers.map { it.name }
        }

        if (args.size == 2) {
            val items = listOf(
                "ironman_helmet",
                "ironman_chestplate",
                "ironman_leggings",
                "ironman_boots",
                "ark_reactor",
                "jarvis",
                "exo_stick",
                "jet_engine_core",
                "jet_engine",
                "repulso_core",
                "repulso_tool",
                "laser_core",
                "laser_staff",
                "energy_snowball",
                "super_carrot",
                "energy_sword",
                "firestick",
                "time_accelerator",
                "tarnstone",
                "lightning_stick",
                "phoenix_feather",
                )
            return items.filter { it.startsWith(args[1], true) }
        }

        return null
    }
}