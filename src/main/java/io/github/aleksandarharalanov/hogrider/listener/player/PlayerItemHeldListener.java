package io.github.aleksandarharalanov.hogrider.listener.player;

import io.github.aleksandarharalanov.hogrider.core.entity.CustomPig;
import io.github.aleksandarharalanov.hogrider.core.RideMovement;
import io.github.aleksandarharalanov.hogrider.core.VehicleValidator;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerListener;

public class PlayerItemHeldListener extends PlayerListener {

    @Override
    public void onItemHeldChange(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        CustomPig pig = VehicleValidator.getCustomPig(player);
        if (pig == null) return;

        RideMovement.startMovementTask(player, pig);
    }
}
