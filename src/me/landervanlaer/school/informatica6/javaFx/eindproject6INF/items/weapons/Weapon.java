package me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons;

import me.landervanlaer.math.Coordinate;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.Item;

public abstract class Weapon extends Item {
    public Weapon(double weight) {
        super(weight);
    }

    public abstract String getName();

    public abstract void attack(Coordinate to);

    @Override
    public String getExtra() {
        return null;
    }
}
