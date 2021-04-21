package me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.shooters;

import me.landervanlaer.math.Coordinate;
import me.landervanlaer.math.Vector;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.shooters.magazines.Magazine;

public class BulletGenerator {
    private final double vel;
    private final int fireRate;
    private final double damage;
    private long prevFire;

    public BulletGenerator(double vel, int fireRate, double damage) {
        this.vel = vel;
        this.fireRate = fireRate;
        this.damage = damage;
    }

    public Bullet generate(Magazine magazine, Coordinate pos, Coordinate headedTo) throws Magazine.EmptyMagazineException, FiringTooFast {
        if(magazine.isEmpty())
            throw new Magazine.EmptyMagazineException();

        if(!canFire())
            throw new FiringTooFast();


        Vector vel = new Vector(pos, headedTo);
        vel.setMag(getVel());

        magazine.fireBullet();
        return new Bullet(getDamage(), pos, vel);
    }

    public double getDamage() {
        return damage;
    }

    public boolean canFire() {
        return getPrevFire() + getFireRate() >= System.currentTimeMillis();
    }

    public void updatePrevFire() {
        setPrevFire(System.currentTimeMillis());
    }

    public long getPrevFire() {
        return prevFire;
    }

    private void setPrevFire(long prevFire) {
        this.prevFire = prevFire;
    }

    public double getVel() {
        return vel;
    }

    public int getFireRate() {
        return fireRate;
    }

    public static class FiringTooFast extends RuntimeException {
    }
}
