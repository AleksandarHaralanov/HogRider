package io.github.aleksandarharalanov.hogrider.core;

import io.github.aleksandarharalanov.hogrider.HogRider;
import io.github.aleksandarharalanov.hogrider.core.entity.CustomPig;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.util.Vector;
import java.util.HashMap;
import java.util.Map;

public final class RideMovement {
    private static final Map<Player, Integer> activeTasks = new HashMap<>();

    private RideMovement() {}

    public static void startMovementTask(Player player, CustomPig pig) {
        stopMovementTask(player);

        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        int taskId = scheduler.scheduleSyncRepeatingTask(HogRider.getInstance(), () -> {

            int moveWhenHeld = HogRider.getConfig().getInt("move-when-held", 260);
            boolean shouldMove = player.getItemInHand().getTypeId() == moveWhenHeld;
            Vector direction = shouldMove ? player.getEyeLocation().getDirection() : null;
            pig.setPathToLookDirection(direction);

            if (!shouldMove) {
                stopMovementTask(player);
            }
        }, 0L, 1L);

        activeTasks.put(player, taskId);
    }

    public static void stopMovementTask(Player player) {
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        Integer taskId = activeTasks.remove(player);
        if (taskId != null) {
            scheduler.cancelTask(taskId);
        }
    }
}
