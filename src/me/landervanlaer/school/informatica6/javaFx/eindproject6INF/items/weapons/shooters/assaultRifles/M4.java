package me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.shooters.assaultRifles;

import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.shooters.BulletGenerator;

public class M4 extends AssaultRifle {
    public static final double BULLET_VELOCITY = 15;
    public static final int FIRE_RATE = 450;
    public static final int DAMAGE = 20;
    public static final double WEIGHT = 4.4;
    public static final String NAME = "M4";

    public M4() {
        super(new BulletGenerator(BULLET_VELOCITY, FIRE_RATE, DAMAGE), WEIGHT);
    }

    @Override
    public String getName() {
        return NAME;
    }
}
