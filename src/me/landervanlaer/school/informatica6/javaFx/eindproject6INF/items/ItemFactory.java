package me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items;

import com.sun.jdi.InternalException;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.Factory;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.shooters.magazines.MagazineFactory;

public class ItemFactory {
    public static Item generateRandom(int weight, int armorProtection, int backpackMaxWeight) {
        switch(Factory.getRandomByWeightValueOf(ItemType.values())) {
            case ARMOR -> {
                return new Armor(armorProtection, weight);
            }
            case BACKPACK -> {
                return new Backpack(backpackMaxWeight, weight);
            }
            case MAGAZINE -> {
                return MagazineFactory.generateRandom();
            }
            case WEAPON -> {
                return WeaponFactory.generateRandom();
            }
            default -> {
                throw new InternalException();
            }
        }
    }
}
