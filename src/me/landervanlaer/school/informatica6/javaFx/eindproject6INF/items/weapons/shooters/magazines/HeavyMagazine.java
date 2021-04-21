package me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.shooters.magazines;

public class HeavyMagazine extends Magazine {
    public static int MAX = 50;
    public static String NAME = "Heavy";

    @Override
    public int getMax() {
        return HeavyMagazine.MAX;
    }

    @Override
    public String getName() {
        return NAME;
    }
}
