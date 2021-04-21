package me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.shooters.submachineGuns;

import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.shooters.BulletGenerator;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.shooters.Shooter;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.shooters.magazines.MediumMagazine;

public abstract class SubmachineGun extends Shooter<MediumMagazine> {
    public SubmachineGun(BulletGenerator bulletGenerator, double weight) {
        super(bulletGenerator, weight);
    }
}
