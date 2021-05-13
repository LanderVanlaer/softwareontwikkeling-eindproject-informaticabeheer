package me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items;

import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.Factory;

public enum ItemType implements Factory.WeightHolder {
    ARMOR(10),
    BACKPACK(10),
    WEAPON(15),
    MAGAZINE(20);

    public final int weight;

    ItemType(int weight) {
        this.weight = weight;
    }

    @Override
    public int getWeight() {
        return weight;
    }
}
