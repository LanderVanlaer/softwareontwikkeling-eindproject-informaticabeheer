package me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.shooters.magazines;

import com.sun.jdi.InternalException;

import static me.landervanlaer.school.informatica6.javaFx.eindproject6INF.Factory.getRandomValueOf;

public class MagazineFactory {
    public static Magazine generateRandom() {
        switch(getRandomEnumValue()) {
            case HEAVY -> {
                return new HeavyMagazine();
            }
            case LIGHT -> {
                return new LightMagazine();
            }
            case MEDIUM -> {
                return new MediumMagazine();
            }
            default -> {
                throw new InternalException();
            }
        }
    }

    public static MagazineType getRandomEnumValue() {
        return getRandomValueOf(MagazineType.values());
    }
}
