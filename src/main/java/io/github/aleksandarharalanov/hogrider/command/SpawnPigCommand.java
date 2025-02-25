package io.github.aleksandarharalanov.hogrider.command;

import io.github.aleksandarharalanov.hogrider.HogRider;
import io.github.aleksandarharalanov.hogrider.core.entity.CustomPig;
import io.github.aleksandarharalanov.hogrider.util.auth.AccessUtil;
import io.github.aleksandarharalanov.hogrider.util.misc.ColorUtil;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.entity.Player;
import net.minecraft.server.World;

public final class SpawnPigCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (AccessUtil.denyIfNotPlayer(sender, HogRider.getInstance())) return true;
        if (!AccessUtil.senderHasPermission(sender, "hogrider.summon", "[HogRider] You don't have permission to summon a pig ride.")) return true;

        Player player = (Player) sender;
        Location location = player.getLocation();
        CraftWorld craftWorld = (CraftWorld) location.getWorld();
        World nmsWorld = craftWorld.getHandle();
        CustomPig pig = new CustomPig(nmsWorld);

        pig.setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
        boolean spawnWithSaddle = HogRider.getConfig().getBoolean("ride.spawn-with-saddle", true);
        if (spawnWithSaddle) {
            pig.setSaddle(true);
        }

        nmsWorld.addEntity(pig);
        player.sendMessage(ColorUtil.translateColorCodes("&aYour ride has been summoned."));

        return true;
    }
}
