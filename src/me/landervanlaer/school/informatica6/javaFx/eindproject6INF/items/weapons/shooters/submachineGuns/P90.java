package me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.shooters.submachineGuns;

import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.shooters.BulletGenerator;

public class P90 extends SubmachineGun {
    public static final double BULLET_VELOCITY = 4;
    public static final int FIRE_RATE = 300;
    public static final int DAMAGE = 8;
    public static final double WEIGHT = 2.5;
    public static final String NAME = "P90";

    public P90() {
        super(new BulletGenerator(BULLET_VELOCITY, FIRE_RATE, DAMAGE), WEIGHT);
    }

    @Override
    public String getName() {
        return NAME;
    }
}
