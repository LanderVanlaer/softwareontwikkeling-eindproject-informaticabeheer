package me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.shooters.magazines;

public class LightMagazine extends Magazine {
    public static int MAX = 30;
    public static String NAME = "Light";

    @Override
    public int getMax() {
        return LightMagazine.MAX;
    }

    @Override
    public String getName() {
        return NAME;
    }
}
