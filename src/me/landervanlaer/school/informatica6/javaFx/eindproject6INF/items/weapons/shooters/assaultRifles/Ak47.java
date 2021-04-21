package me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.shooters.assaultRifles;

import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.shooters.BulletGenerator;

public class Ak47 extends AssaultRifle {
    public static final double BULLET_VELOCITY = 10;
    public static final int FIRE_RATE = 400;
    public static final int DAMAGE = 26;
    public static final double WEIGHT = 4;
    public static final String NAME = "AK-47";

    public Ak47() {
        super(new BulletGenerator(BULLET_VELOCITY, FIRE_RATE, DAMAGE), WEIGHT);
    }

    @Override
    public String getName() {
        return NAME;
    }
}
