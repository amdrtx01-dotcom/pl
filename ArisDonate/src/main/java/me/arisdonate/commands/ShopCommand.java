package me.arisdonate.commands;

import me.arisdonate.ArisDonatePlugin;
import me.arisdonate.util.BaseCommand;
import me.arisdonate.util.Msg;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class ShopCommand extends BaseCommand {
    public ShopCommand(ArisDonatePlugin plugin) { super(plugin); }

    @Override
    protected void execute(CommandSender sender, Command command, String label, String[] args) {
        Player p = requirePlayer(sender);
        if (p == null) return;
        List<String> shopUrls = plugin.getConfig().getStringList("shop-links");
        if (shopUrls.isEmpty()) {
            p.sendMessage(Msg.parse("&6Магазин сервера:"));
            p.sendMessage(Msg.parse("&7Свяжитесь с администрацией для покупки доната."));
            p.sendMessage(Component.text("[Открыть Discord]").color(net.kyori.adventure.text.format.NamedTextColor.AQUA)
                    .clickEvent(ClickEvent.openUrl(plugin.getConfig().getString("discord-url", "https://discord.gg/"))));
            return;
        }
        for (String url : shopUrls) {
            p.sendMessage(Component.text("→ " + url).color(net.kyori.adventure.text.format.NamedTextColor.AQUA)
                    .clickEvent(ClickEvent.openUrl(url)));
        }
    }
}
