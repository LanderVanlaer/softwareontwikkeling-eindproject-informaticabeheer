package me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.shooters.pistols;

import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.shooters.BulletGenerator;

public class Glock extends Pistol {
    public static final double BULLET_VELOCITY = 5;
    public static final int FIRE_RATE = 750;
    public static final int DAMAGE = 20;
    public static final double WEIGHT = .5;
    public static final String NAME = "Glock";

    public Glock() {
        super(new BulletGenerator(BULLET_VELOCITY, FIRE_RATE, DAMAGE), WEIGHT);
    }

    @Override
    public String getName() {
        return NAME;
    }
}
