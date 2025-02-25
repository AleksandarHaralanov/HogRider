package io.github.aleksandarharalanov.hogrider;

import io.github.aleksandarharalanov.hogrider.command.SpawnPigCommand;
import io.github.aleksandarharalanov.hogrider.listener.player.PlayerItemHeldListener;
import io.github.aleksandarharalanov.hogrider.listener.vehicle.VehicleCollisionListener;
import io.github.aleksandarharalanov.hogrider.listener.vehicle.VehicleExitListener;
import io.github.aleksandarharalanov.hogrider.util.config.ConfigUtil;
import io.github.aleksandarharalanov.hogrider.util.log.UpdateUtil;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import io.github.aleksandarharalanov.hogrider.util.log.LogUtil;

public class HogRider extends JavaPlugin {

    private static ConfigUtil config;
    private static HogRider plugin;

    @Override
    public void onEnable() {
        // Update Checks on GitHub Repository
        UpdateUtil.checkAvailablePluginUpdates(this, "https://api.github.com/repos/AleksandarHaralanov/HogRider/releases/latest");

        // Singleton Instance
        plugin = this;

        // Configurations
        config = new ConfigUtil(this, "config.yml");
        config.load();

        PluginManager pM = getServer().getPluginManager();

        // Player Listeners
        final PlayerItemHeldListener pIHL = new PlayerItemHeldListener();
        pM.registerEvent(Event.Type.PLAYER_ITEM_HELD, pIHL, Event.Priority.Normal, this);

        // Vehicle Listeners
        final VehicleCollisionListener vCL = new VehicleCollisionListener();
        final VehicleExitListener vEL = new VehicleExitListener();
        pM.registerEvent(Event.Type.VEHICLE_COLLISION_BLOCK, vCL, Event.Priority.Normal, this);
        pM.registerEvent(Event.Type.VEHICLE_EXIT, vEL, Event.Priority.Normal, this);

        // Main Command
        final SpawnPigCommand command = new SpawnPigCommand();
        getCommand("hog").setExecutor(command);

        LogUtil.logConsoleInfo(String.format("[%s] v%s Enabled.", getDescription().getName(), getDescription().getVersion()));
    }

    @Override
    public void onDisable() {
        LogUtil.logConsoleInfo(String.format("[%s] v%s Disabled.", getDescription().getName(), getDescription().getVersion()));
    }

    public static HogRider getInstance() {
        return plugin;
    }

    public static ConfigUtil getConfig() {
        return config;
    }
}