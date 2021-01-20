package me.landervanlaer.school.informatica6.javaFx.eindproject6INF;

import me.landervanlaer.math.Number;

import java.util.HashMap;

public class Held {
    public final static double STRENGTH_AVARAGE = 10.0;
    private final static int[] DEFAULT_CAPACITY = new int[]{115, 130, 150, 175, 200, 230, 260, 300, 350, 400};
    public static int DEFAULT_PROTECTION = 10;
    private final HashMap<AnchorPoints, Object> anchors = new HashMap<>();
    private double strength = STRENGTH_AVARAGE;
    private boolean fighting = false;
    private String name;
    private int maxHitpoints;
    private int hitpoints;
    private int capacity = getCapacityByStrength(this.getStrength());
    private int protection = DEFAULT_PROTECTION;

    public static boolean isValidProtection(int protection) {
        return protection > 0;
    }

    private static int getClosestPrimeNumber(int number) {
        while(!Number.isPrimeNumber(number) && number > 1) {
            --number;
        }
        return number;
    }

    private static int getCapacityByStrength(double strength) {
        if(strength < 0)
            return 0;
        else if(strength < 11)
            return (int) (strength * 10);
        else if(strength > 21)
            return DEFAULT_CAPACITY[(int) strength];
        else
            return getCapacityByStrength(strength - 10) * 4;
    }

    public static boolean isValidMaxHitpoints(int maxHitpoints) {
        return maxHitpoints > 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name.matches("[A-Z][a-zA-Z ]+'?[a-zA-Z ]*'?[a-zA-Z ]*"))
            this.name = name;
        else
            throw new IllegalArgumentException("Naam moet beginnen met een hoofdletter en mag alleen hoofdletters, kleine letters, spaties en ' bevatten");
    }

    public int getHitpoints() {
        return hitpoints;
    }

    public void setHitpoints(int hitpoints) {
        if(hitpoints <= 0)
            hitpoints = 0;
        else if(hitpoints >= this.getMaxHitpoints())
            hitpoints = this.getMaxHitpoints();

        if(!isFighting())
            hitpoints = getClosestPrimeNumber(hitpoints);

        this.hitpoints = hitpoints;
    }

    public void multiplyStrength(int multiplier) {
        setStrength(getStrength() * multiplier);
    }

    public void divideStrength(int denominator) {
        setStrength(getStrength() / denominator);
    }

    public double getStrength() {
        return strength;
    }

    private void setStrength(double strength) {
        this.strength = strength;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getProtection() {
        return protection;
    }

    public void setProtection(int protection) {
        if(!isValidProtection(protection))
            throw new IllegalArgumentException("The given protection is not valid.");
        this.protection = protection;
    }

    public boolean isFighting() {
        return fighting;
    }

    public void setFighting(boolean fighting) {
        this.fighting = fighting;
    }

    public int getMaxHitpoints() {
        return maxHitpoints;
    }

    public void setMaxHitpoints(int maxHitpoints) {
        if(!isValidMaxHitpoints(maxHitpoints))
            throw new IllegalArgumentException("Maxpoints cannot be 0 or less");
            this.maxHitpoints = maxHitpoints;
    }
}
