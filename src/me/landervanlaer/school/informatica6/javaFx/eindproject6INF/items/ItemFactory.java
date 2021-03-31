package me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items;

import com.sun.jdi.InternalException;
import me.landervanlaer.math.Number;

public class ItemFactory {
    public static Item getRandomItem(double weight, double maxWeight, int protection, int damage) {
        switch(Number.getRandom(0, 3)) {
            case 0 -> {
                return new Armor(protection, weight);
            }
            case 1 -> {
                return new Weapon(damage, weight);
            }
            case 2 -> {
                return new Backpack(maxWeight, weight);
            }
        }
        throw new InternalException();
    }
}
