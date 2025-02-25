package io.github.aleksandarharalanov.hogrider.core.entity;

import io.github.aleksandarharalanov.hogrider.HogRider;
import io.github.aleksandarharalanov.hogrider.util.log.LogUtil;
import net.minecraft.server.EntityPig;
import net.minecraft.server.EntityTypes;
import net.minecraft.server.World;
import org.bukkit.util.Vector;

import java.lang.reflect.Method;

public class CustomPig extends EntityPig {

    static {
        try {
            Method register = EntityTypes.class.getDeclaredMethod("a", Class.class, String.class, int.class);
            register.setAccessible(true);
            register.invoke(null, CustomPig.class, "Pig", 90);
        } catch (Exception e) {
            LogUtil.logConsoleSevere(String.format("[HogRider] Could not register custom entity class: %s", e.getMessage()));
        }
    }

    public CustomPig(World world) {
        super(world);
    }

    @Override
    public void c_() {}

    @Override
    public int j() {
        return 0;
    }

    @Override
    public void O() {
        this.motY = HogRider.getConfig().getDouble("ride.jump", 0.42F);
    }

    public void setPathToLookDirection(Vector direction) {
        if (direction == null) {
            this.motX = 0F;
            this.motY = 0F;
            this.motZ = 0F;
            return;
        }

        double moveSpeed = HogRider.getConfig().getDouble("ride.move", 0.25F);

        this.motX = direction.getX() * moveSpeed;
        this.motY = 0F;
        this.motZ = direction.getZ() * moveSpeed;

        this.yaw = (float) Math.toDegrees(Math.atan2(-this.motX, this.motZ));
    }
}
