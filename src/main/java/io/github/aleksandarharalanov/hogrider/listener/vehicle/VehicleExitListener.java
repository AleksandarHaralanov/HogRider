package io.github.aleksandarharalanov.hogrider.listener.vehicle;

import io.github.aleksandarharalanov.hogrider.core.VehicleValidator;
import io.github.aleksandarharalanov.hogrider.util.misc.ColorUtil;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.event.vehicle.VehicleListener;

public class VehicleExitListener extends VehicleListener {

    @Override
    public void onVehicleExit(VehicleExitEvent event) {
        Vehicle vehicle = event.getVehicle();
        if (!VehicleValidator.isValidCustomPigRide(vehicle)) return;

        Player player = (Player) event.getExited();
        player.sendMessage(ColorUtil.translateColorCodes("&aYour ride has been removed."));
        vehicle.remove();
    }
}
