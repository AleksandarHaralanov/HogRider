package io.github.aleksandarharalanov.hogrider.core;

import io.github.aleksandarharalanov.hogrider.core.entity.CustomPig;
import org.bukkit.craftbukkit.entity.CraftPig;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vehicle;

public final class VehicleValidator {

    private VehicleValidator() {}

    public static CustomPig getCustomPig(Player player) {
        if (!isValidCustomPigRide(player)) return null;

        CraftPig craftPig = (CraftPig) player.getVehicle();
        return (CustomPig) craftPig.getHandle();
    }

    public static CustomPig getCustomPig(Vehicle vehicle) {
        if (!isValidCustomPigRide(vehicle)) return null;

        CraftPig craftPig = (CraftPig) vehicle;
        return (CustomPig) craftPig.getHandle();
    }

    public static boolean isValidCustomPigRide(Player player) {
        if (!player.isInsideVehicle()) return false;

        Vehicle vehicle = player.getVehicle();
        if (!(vehicle instanceof CraftPig)) return false;

        CraftPig craftPig = (CraftPig) vehicle;
        return craftPig.getHandle() instanceof CustomPig;
    }

    public static boolean isValidCustomPigRide(Vehicle vehicle) {
        if (!(vehicle instanceof CraftPig)) return false;

        CraftPig craftPig = (CraftPig) vehicle;
        return craftPig.getHandle() instanceof CustomPig;
    }
}
