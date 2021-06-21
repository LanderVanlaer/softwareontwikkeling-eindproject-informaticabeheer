package me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.shooters.pistols;

import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.config.ConfigHandler;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.shooters.BulletGenerator;

public class Glock extends Pistol {
    public static final String NAME = "Glock";

    public Glock() {
        super(new BulletGenerator(
                        ConfigHandler.getDouble("items.weapons.shooters.pistols.Glock.BULLET_VELOCITY"),
                        ConfigHandler.getInt("items.weapons.shooters.pistols.Glock.FIRE_RATE"),
                        ConfigHandler.getInt("items.weapons.shooters.pistols.Glock.DAMAGE")),
                ConfigHandler.getDouble("items.weapons.shooters.pistols.Glock.WEIGHT"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}
