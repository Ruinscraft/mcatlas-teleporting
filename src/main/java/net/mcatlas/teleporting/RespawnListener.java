package net.mcatlas.teleporting;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class RespawnListener implements Listener {

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        final Player player = event.getPlayer();

        if (player.getBedSpawnLocation() == null) {

            Location newLocation = TeleportingPlugin.findRandomLocation(TeleportingPlugin.getMainWorld());

            event.setRespawnLocation(newLocation);

            TeleportingPlugin.get().getServer().getScheduler().runTaskLater(TeleportingPlugin.get(), () -> {
                player.playSound(newLocation, Sound.BLOCK_BEACON_POWER_SELECT, 2.0F, 0.5F);
                player.sendMessage(ChatColor.GRAY + "You've spawned somewhere on Earth.");
                player.sendMessage(ChatColor.GRAY + "View where: " + ChatColor.YELLOW + "https://mcatlas.net/map");
            }, 40);
        }
    }

}
