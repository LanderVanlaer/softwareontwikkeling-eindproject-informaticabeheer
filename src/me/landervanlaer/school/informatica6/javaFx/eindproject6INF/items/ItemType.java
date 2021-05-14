package me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items;

import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.Factory;

public enum ItemType implements Factory.WeightHolder {
    ARMOR(13),
    BACKPACK(10),
    WEAPON(17),
    MAGAZINE(35);

    public final int weight;

    ItemType(int weight) {
        this.weight = weight;
    }

    @Override
    public int getWeight() {
        return weight;
    }
}
