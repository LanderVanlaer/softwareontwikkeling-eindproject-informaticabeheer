package me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.shooters.pistols;

import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.shooters.BulletGenerator;

public class DesertEagle extends Pistol {
    public static final double BULLET_VELOCITY = 7.5;
    public static final int FIRE_RATE = 1200;
    public static final int DAMAGE = 70;
    public static final double WEIGHT = 1.5;
    public static final String NAME = "Desert Eagle";

    public DesertEagle() {
        super(new BulletGenerator(BULLET_VELOCITY, FIRE_RATE, DAMAGE), WEIGHT);
    }

    @Override
    public String getName() {
        return NAME;
    }
}
