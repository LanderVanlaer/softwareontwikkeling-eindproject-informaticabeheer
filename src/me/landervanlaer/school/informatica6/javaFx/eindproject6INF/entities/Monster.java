package me.landervanlaer.school.informatica6.javaFx.eindproject6INF.entities;

import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.Armor;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.Weapon;

public class Monster extends Entity {
    public static final int DEFAULT_DAMAGE = 50;
    public static final int DEFAULT_PROTECTION = 20;
    private final Armor armor;
    private final Weapon claws;

    public Monster(String name, int maxHitpoints, int protection) {
        super(name, maxHitpoints, protection);
        if(!name.matches("[A-Z][a-zA-Z' ]*")) throw new IllegalArgumentException("Invalid name");
        this.armor = new Armor(DEFAULT_PROTECTION, 0);
        this.claws = new Weapon(DEFAULT_DAMAGE, 0);
    }

    public static boolean isValidName(String name) {
        return name.matches("[A-Z][a-zA-Z' ]*");
    }

    @Override
    public boolean canHaveName(String name) {
        return isValidName(name);
    }

    @Override
    public double getStrengthOfHit() {
        return 0; // TODO: 31/03/2021 getStrengthOfHit Monster
    }

    @Override
    public int getCapacity() {
        return 0; // TODO: 31/03/2021 getCapacity Monster
    }

    @Override
    public int getProtectionForHit() {
        // TODO: 31/03/2021 getProtectionForHit
        return 0;
    }

    public Armor getArmor() {
        return armor;
    }

    public Weapon getClaws() {
        return claws;
    }
}
