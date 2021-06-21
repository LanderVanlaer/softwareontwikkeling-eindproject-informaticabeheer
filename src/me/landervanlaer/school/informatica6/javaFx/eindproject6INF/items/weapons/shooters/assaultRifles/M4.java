package me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.shooters.assaultRifles;

import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.config.ConfigHandler;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.shooters.BulletGenerator;

public class M4 extends AssaultRifle {
    public static final String NAME = "M4";

    public M4() {
        super(new BulletGenerator(
                        ConfigHandler.getDouble("items.weapons.shooters.assaultRifles.M4.BULLET_VELOCITY"),
                        ConfigHandler.getInt("items.weapons.shooters.assaultRifles.M4.FIRE_RATE"),
                        ConfigHandler.getInt("items.weapons.shooters.assaultRifles.M4.DAMAGE")),
                ConfigHandler.getDouble("items.weapons.shooters.assaultRifles.M4.WEIGHT"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}
