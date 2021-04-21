package me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.shooters.submachineGuns;

import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.shooters.BulletGenerator;

public class Uzi extends SubmachineGun {
    public static final double BULLET_VELOCITY = 5;
    public static final int FIRE_RATE = 350;
    public static final int DAMAGE = 10;
    public static final double WEIGHT = 3.6;
    public static final String NAME = "Uzi";

    public Uzi() {
        super(new BulletGenerator(BULLET_VELOCITY, FIRE_RATE, DAMAGE), WEIGHT);
    }

    @Override
    public String getName() {
        return NAME;
    }
}
