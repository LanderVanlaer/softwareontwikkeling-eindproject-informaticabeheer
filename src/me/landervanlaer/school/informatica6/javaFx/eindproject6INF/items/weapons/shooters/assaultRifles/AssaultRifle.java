package me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.shooters.assaultRifles;

import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.shooters.BulletGenerator;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.shooters.Shooter;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.shooters.magazines.HeavyMagazine;

public abstract class AssaultRifle extends Shooter<HeavyMagazine> {
    public AssaultRifle(BulletGenerator bulletGenerator, double weight) {
        super(bulletGenerator, weight);
    }
}