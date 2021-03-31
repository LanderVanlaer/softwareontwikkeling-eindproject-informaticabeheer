package me.landervanlaer.school.informatica6.javaFx.eindproject6INF.entities;

public class Monster extends Entity {
    protected Monster(String name, int maxHitpoints, int protection) {
        super(name, maxHitpoints, protection);
        if(!name.matches("[A-Z][a-zA-Z' ]*")) throw new IllegalArgumentException("Invalid name");
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
        return 0;
    }
}
