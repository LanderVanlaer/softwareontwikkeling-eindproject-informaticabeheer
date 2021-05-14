package me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.shooters.magazines;

public class MediumMagazine extends Magazine {
    public static int MAX = 12;
    public static String NAME = "Medium Magazine";

    @Override
    public int getMax() {
        return MediumMagazine.MAX;
    }

    @Override
    public String getName() {
        return NAME;
    }
}
