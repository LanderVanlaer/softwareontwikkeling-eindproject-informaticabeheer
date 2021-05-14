package me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons;

import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.Item;

public abstract class Weapon extends Item {
    public Weapon(double weight) {
        super(weight);
    }

    public abstract String getName();

    @Override
    public String getExtra() {
        return null;
    }
}
