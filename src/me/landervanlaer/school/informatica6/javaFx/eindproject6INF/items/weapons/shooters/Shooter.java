package me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.shooters;

import me.landervanlaer.math.Coordinate;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.Weapon;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.shooters.magazines.Magazine;

public abstract class Shooter<M extends Magazine> extends Weapon {
    private final BulletGenerator bulletGenerator;
    private M magazine;

    public Shooter(BulletGenerator bulletGenerator, double weight) {
        super(weight);
        this.bulletGenerator = bulletGenerator;
    }

    public Magazine getMagazine() {
        return magazine;
    }

    public void setMagazine(M magazine) {
        this.magazine = magazine;
    }

    public BulletGenerator getBulletGenerator() {
        return bulletGenerator;
    }

    public Bullet getBullet(Coordinate heading) throws Magazine.EmptyMagazineException {
        try {
            return getBulletGenerator().generate(getMagazine(), getHolder().getPos(), heading);
        } catch(BulletGenerator.FiringTooFast ignored) {
            return null;
        }
    }
}
