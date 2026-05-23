package me.arisdonate.commands;

import me.arisdonate.ArisDonatePlugin;
import me.arisdonate.managers.KitManager;
import me.arisdonate.util.BaseCommand;
import me.arisdonate.util.Msg;
import me.arisdonate.util.TimeUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class KitCommand extends BaseCommand {
    public KitCommand(ArisDonatePlugin plugin) { super(plugin); }

    @Override
    protected void execute(CommandSender sender, Command command, String label, String[] args) {
        Player p = requirePlayer(sender);
        if (p == null) return;
        if (args.length == 0) {
            p.sendMessage(Msg.parse("&7Использование: &e/kit <id>"));
            StringBuilder sb = new StringBuilder();
            for (KitManager.Kit k : plugin.getKitManager().all()) sb.append(k.id).append(", ");
            p.sendMessage(Msg.parse("&6Доступные киты: &7" + sb));
            return;
        }
        KitManager.Kit kit = plugin.getKitManager().getKit(args[0]);
        if (kit == null) { p.sendMessage(Msg.parse("&cКит не найден.")); return; }
        if (!p.hasPermission("arisdonate.kit." + kit.id)) {
            p.sendMessage(Msg.parse("&cНет права на этот кит."));
            return;
        }
        long left = plugin.getKitManager().cooldownLeft(p, kit.id);
        if (left > 0) {
            p.sendMessage(Msg.parse("&cКит на кулдауне: ещё &e" + TimeUtil.fmt(left)));
            return;
        }
        plugin.getKitManager().giveKit(p, kit);
        p.sendMessage(Msg.parse("&aВыдан кит &e" + kit.id));
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            List<String> ids = new ArrayList<>();
            for (KitManager.Kit k : plugin.getKitManager().all()) ids.add(k.id);
            return ids;
        }
        return List.of();
    }
}
