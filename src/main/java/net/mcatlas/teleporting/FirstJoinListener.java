package net.mcatlas.teleporting;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class FirstJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPlayedBefore()) {
            player.teleport(TeleportingPlugin.findRandomLocation(player.getWorld()));

//            TeleportingPlugin.get().getServer().getScheduler().runTaskLater(TeleportingPlugin.get(), () -> {
//                player.playSound(player.getLocation(), Sound.BLOCK_BEACON_POWER_SELECT, 2.0F, 0.5F);
//                player.sendMessage(ChatColor.GRAY + "You've spawned somewhere on Earth.");
//                player.sendMessage(ChatColor.GRAY + "View where: " + ChatColor.YELLOW + "https://mcatlas.net/map");
//            }, 60);

            TeleportingPlugin.get().getServer().getScheduler().runTaskLater(TeleportingPlugin.get(), () -> {
                player.sendTitle(ChatColor.GRAY + "Welcome to " + ChatColor.DARK_BLUE + "MCATLAS", "");
                TeleportingPlugin.get().getServer().getScheduler().runTaskLater(TeleportingPlugin.get(), () -> {
                    player.sendTitle(ChatColor.GOLD + "You're somewhere on Earth", "View the map with /map");
                }, 80L);
            }, 60L);
        }
    }

}
