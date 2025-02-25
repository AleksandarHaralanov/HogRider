package io.github.aleksandarharalanov.hogrider.listener.vehicle;

import io.github.aleksandarharalanov.hogrider.core.entity.CustomPig;
import io.github.aleksandarharalanov.hogrider.core.VehicleValidator;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.vehicle.VehicleBlockCollisionEvent;
import org.bukkit.event.vehicle.VehicleListener;

public class VehicleCollisionListener extends VehicleListener {

    @Override
    public void onVehicleBlockCollision(VehicleBlockCollisionEvent event) {
        Vehicle vehicle = event.getVehicle();
        CustomPig pig = VehicleValidator.getCustomPig(vehicle);
        if (pig == null) return;

        pig.O();
    }
}

