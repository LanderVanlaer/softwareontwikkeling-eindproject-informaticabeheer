package me.landervanlaer.school.informatica6.javaFx.eindproject6INF.entities;

import me.landervanlaer.math.Number;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.AnchorPoint;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.*;

import java.util.Arrays;
import java.util.Collection;

public class Monster extends Entity {
    public static final int DEFAULT_DAMAGE = 42;
    public static final int DEFAULT_PROTECTION = 5;
    private final Armor armor;
    private final Weapon claws;

    public Monster(String name, int maxHitpoints, int protection, boolean randomItems) {
        super(name, maxHitpoints, protection);
        if(!name.matches("[A-Z][a-zA-Z' ]*")) throw new IllegalArgumentException("Invalid name");
        this.armor = new Armor(DEFAULT_PROTECTION, 0);
        this.claws = new Weapon(DEFAULT_DAMAGE, 0);

        if(randomItems) {
            for(int i = 0; i < Number.getRandom(1, AnchorPoint.values().length); i++) {
                try {
                    addItem(ItemFactory.getRandomItem(10, 30, 10, 18));
                } catch(RuntimeException ignored) {
                }
            }
        }
    }

    public Monster(String name, int maxHitpoints, int protection) {
        this(name, maxHitpoints, protection, false);
    }

    public static boolean isValidName(String name) {
        return name.matches("[A-Z][a-zA-Z' ]*");
    }

    public static AnchorPoint getRandomAnchorpoint() {
        return AnchorPoint.values()[Number.getRandom(0, AnchorPoint.values().length)];
    }

    public void collect(Item[] items) {
        for(Item item : items) {
            if(canHaveItem(item)) {
                this.addItem(item);
            } else {
                if(Number.getRandom(0, 10) <= 3) {
                    //swap weapon with another weapon
                    final AnchorPoint a = getRandomAnchorpoint();
                    Item prev = this.takeItem(a);

                    if(canHaveItem(item)) {
                        putItem(item, a);

                        if(!(prev instanceof Backpack))
                            prev.destroy();
                    } else {
                        putItem(prev, a);
                    }
                } else if(!(item instanceof Backpack))
                    item.destroy();
            }
        }
    }

    @Override
    public void hit(Entity entity) {
        final int random = Number.getRandom(0, 101);
        final int damage = Math.min(random, getHitpoints());
        if(damage >= entity.getProtectionForHit()) {
            //raak
            entity.reduceHitpoints((int) (getDamage() + this.getStrengthOfHit()));

            if(entity.getHitpoints() == 0) {
                final Collection<Item> items = entity.getAnchors().values();
                final Object[] arr = items.toArray();
                collect(Arrays.copyOf(arr, arr.length, Item[].class));
            }
        }
    }

    @Override
    public boolean canHaveName(String name) {
        return isValidName(name);
    }

    @Override
    public double getStrengthOfHit() {
        return (getStrength() - 5D) / 3D;
    }

    @Override
    public int getCapacity() {
        return (int) (getStrength() * 9D);
    }

    @Override
    public int getProtectionForHit() {
        return getProtection() + getArmor().getProtection();
    }

    public int getDamage() {
        return getClaws().getDamage();
    }

    public Armor getArmor() {
        return armor;
    }

    public Weapon getClaws() {
        return claws;
    }
}
