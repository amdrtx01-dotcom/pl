package me.arisdonate.commands;

import me.arisdonate.ArisDonatePlugin;
import me.arisdonate.managers.KitManager;
import me.arisdonate.util.BaseCommand;
import me.arisdonate.util.Msg;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class KitListCommand extends BaseCommand {
    public KitListCommand(ArisDonatePlugin plugin) { super(plugin); }

    @Override
    protected void execute(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage(Msg.parse("&6Доступные киты:"));
        for (KitManager.Kit k : plugin.getKitManager().all()) {
            sender.sendMessage(Msg.parse(" &f• &e" + k.id + " &7(" + k.displayName + ")"));
        }
    }
}
