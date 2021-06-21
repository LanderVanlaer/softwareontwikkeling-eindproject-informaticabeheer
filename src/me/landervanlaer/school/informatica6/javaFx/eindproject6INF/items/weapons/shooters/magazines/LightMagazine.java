package me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.shooters.magazines;

import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.config.ConfigHandler;

public class LightMagazine extends Magazine {
    public static String NAME = "Light Magazine";

    @Override
    public int getMax() {
        return ConfigHandler.getInt("items.weapons.shooters.magazines.LightMagazine.MAX");
    }

    @Override
    public String getName() {
        return NAME;
    }
}
