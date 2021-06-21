package me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items;

import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.Factory;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.config.ConfigHandler;

public enum ItemType implements Factory.WeightHolder {
    ARMOR("items.ItemType.ARMOR"),
    BACKPACK("items.ItemType.BACKPACK"),
    WEAPON("items.ItemType.WEAPON"),
    MAGAZINE("items.ItemType.MAGAZINE");

    public final String weightConfigName;

    ItemType(String weightConfigName) {
        this.weightConfigName = weightConfigName;
    }

    @Override
    public int getWeight() {
        return ConfigHandler.getInt(weightConfigName);
    }
}
