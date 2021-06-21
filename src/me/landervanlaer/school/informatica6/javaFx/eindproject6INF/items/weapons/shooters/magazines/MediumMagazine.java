package me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.shooters.magazines;

import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.config.ConfigHandler;

public class MediumMagazine extends Magazine {
    public static String NAME = "Medium Magazine";

    @Override
    public int getMax() {
        return ConfigHandler.getInt("items.weapons.shooters.magazines.MediumMagazine.MAX");
    }

    @Override
    public String getName() {
        return NAME;
    }
}
