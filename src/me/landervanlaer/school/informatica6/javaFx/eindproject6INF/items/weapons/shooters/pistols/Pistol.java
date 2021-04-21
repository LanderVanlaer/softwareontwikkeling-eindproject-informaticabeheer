package me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.shooters.pistols;

import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.shooters.BulletGenerator;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.shooters.Shooter;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.shooters.magazines.LightMagazine;

public abstract class Pistol extends Shooter<LightMagazine> {
    public Pistol(BulletGenerator bulletGenerator, double weight) {
        super(bulletGenerator, weight);
    }
}
