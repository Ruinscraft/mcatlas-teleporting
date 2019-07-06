package net.mcatlas.teleporting;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.ThreadLocalRandom;

// overworld    43008   x   21504
// nether       5376    x   2688
public class TeleportingPlugin extends JavaPlugin {

    private static final int X_MIN = -21504;
    private static final int X_MAX = 21504;
    private static final int Y_MIN = -10752;
    private static final int Y_MAX = 10752;

    @Override
    public void onEnable() {
        instance = this;
        getServer().getPluginManager().registerEvents(new FirstJoinListener(), this);
        getServer().getPluginManager().registerEvents(new RespawnListener(), this);
        getCommand("randomtp").setExecutor(new RandomTPCommand());
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public static World getMainWorld() {
        return Bukkit.getWorlds().get(0);
    }

    public static Location findRandomLocation(World world) {
        final double randomX = ThreadLocalRandom.current().nextDouble(X_MIN, X_MAX);
        final double randomY = ThreadLocalRandom.current().nextDouble(Y_MIN, Y_MAX);

        Location candidate = world.getWorldBorder().getCenter().add(randomX, 0, randomY);
        Block highestBlock = world.getHighestBlockAt(candidate);

        switch (highestBlock.getRelative(BlockFace.DOWN).getType()) {
            case WATER:
            case LAVA:
            case MAGMA_BLOCK:
            case SNOW_BLOCK:
            case SNOW:
            case CACTUS:
            case ACACIA_LEAVES:
            case DARK_OAK_LEAVES:
            case BIRCH_LEAVES:
            case JUNGLE_LEAVES:
            case OAK_LEAVES:
            case SPRUCE_LEAVES:
            case ICE:
            case BLUE_ICE:
            case PACKED_ICE:
            case SAND:
            case SANDSTONE:
            case KELP_PLANT:
                return findRandomLocation(world);
        }

        return highestBlock.getLocation();
    }

    private static TeleportingPlugin instance;

    public static TeleportingPlugin get() {
        return instance;
    }

}
