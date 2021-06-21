package me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.shooters.pistols;

import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.config.ConfigHandler;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.shooters.BulletGenerator;

public class DesertEagle extends Pistol {
    public static final String NAME = "Desert Eagle";

    public DesertEagle() {
        super(new BulletGenerator(
                        ConfigHandler.getDouble("items.weapons.shooters.pistols.DesertEagle.BULLET_VELOCITY"),
                        ConfigHandler.getInt("items.weapons.shooters.pistols.DesertEagle.FIRE_RATE"),
                        ConfigHandler.getInt("items.weapons.shooters.pistols.DesertEagle.DAMAGE")),
                ConfigHandler.getDouble("items.weapons.shooters.pistols.DesertEagle.WEIGHT"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}
