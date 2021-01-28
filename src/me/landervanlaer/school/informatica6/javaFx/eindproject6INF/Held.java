package me.landervanlaer.school.informatica6.javaFx.eindproject6INF;

import me.landervanlaer.math.Number;

import java.util.HashMap;

public class Held {
    public final static double STRENGTH_AVARAGE = 20.0;
    private final static int[] DEFAULT_CAPACITY = new int[]{115, 130, 150, 175, 200, 230, 260, 300, 350, 400};

    public static int DEFAULT_PROTECTION = 10;

    private final HashMap<AnchorPoints, Object> anchors;
    private double strength;
    private boolean fighting;
    private String name;
    private int maxHitpoints;
    private int hitpoints;
    private int capacity;
    private int protection;

    public Held() {
        this("Held", 100);
    }

    public Held(String name, int hitpoints) {
        if(!isValidName(name))
            throw new IllegalArgumentException("Naam moet beginnen met een hoofdletter en mag alleen hoofdletters, kleine letters, spaties en ' bevatten");

        if(!isValidMaxHitpoints(hitpoints))
            throw new IllegalArgumentException("Hitpoints cannot be 0 or less");

        if(!isValidProtection(DEFAULT_PROTECTION))
            throw new IllegalArgumentException("The given protection is not valid.");

        this.name = name;
        this.hitpoints = hitpoints;

        this.maxHitpoints = this.hitpoints;
        this.protection = DEFAULT_PROTECTION;
        this.fighting = false;
        this.anchors = new HashMap<>();
        this.strength = STRENGTH_AVARAGE;

        this.capacity = getCapacityByStrength(this.strength);
    }

    public static boolean isValidName(String name) {
        return name.matches("[A-Z][a-zA-Z ]+'?[a-zA-Z ]*'?[a-zA-Z ]*");
    }

    public static boolean isValidProtection(int protection) {
        return protection >= 0 && protection <= 20;
    }

    protected static int getClosestPrimeNumber(int number) {
        while(!Number.isPrimeNumber(number) && number > 1) {
            --number;
        }
        return number;
    }

    public static int getCapacityByStrength(double strength) {
        if(strength < 0)
            return 0;
        else if(strength < 11)
            return (int) (strength * 10);
        else if(strength < 21)
            return DEFAULT_CAPACITY[(int) strength - 11];
        else
            return getCapacityByStrength(strength - 10) * 4;
    }

    public static boolean isValidMaxHitpoints(int maxHitpoints) {
        return maxHitpoints > 0;
    }

    public void hit(Held defender) {
        final int random = Number.getRandom(0, 21);
        if(random >= defender.getProtection()) {
            final int defenderHitpoints = defender.reduceHitpoints((int) getHitStrength());

            // DEATHBLOW
            if(defenderHitpoints <= 0) {
                setHitpoints((int) (getHitpoints() + ((double) (getMaxHitpoints() - getHitpoints()) * (Number.getRandom(0, 101) / 100D))));
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(isValidName(name))
            this.name = name;
        else
            throw new IllegalArgumentException("Naam moet beginnen met een hoofdletter en mag alleen hoofdletters, kleine letters, spaties en ' bevatten");
    }

    public int reduceHitpoints(int subtractor) {
        setHitpoints(getHitpoints() - subtractor);
        return getHitpoints();
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

    public double getHitStrength() {
        return (getStrength() - 10D) / 2D;
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

    private void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    protected void updateCapacity() {
        setCapacity(getCapacityByStrength(getStrength()));
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
