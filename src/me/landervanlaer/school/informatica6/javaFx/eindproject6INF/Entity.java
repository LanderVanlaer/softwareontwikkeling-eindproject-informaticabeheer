package me.landervanlaer.school.informatica6.javaFx.eindproject6INF;

import me.landervanlaer.math.Number;

import java.text.MessageFormat;
import java.util.HashMap;

public abstract class Entity {
    public static final int DEFAULT_PROTECTION = 10;

    private final HashMap<AnchorPoints, Item> anchors;
    private String name;
    private double strength;
    private boolean fighting;
    private int hitpoints;
    private int maxHitpoints;
    private int protection;

    protected Entity() {
        this.hitpoints = this.maxHitpoints;
        this.strength = DEFAULT_PROTECTION;
    }

    public static boolean isValidMaxHitpoints(int i) {
        return i > 0;
    }

    public static boolean isValidProtection(int i) {
        return i > 0;
    }

    public boolean canHaveHitpoints(int i) {
        return i >= 0 && i <= getMaxHitpoints() && (isFighting() || Number.isPrimeNumber(i));
    }

    public void collect() {
        // TODO: 29/03/2021 collect
    }

    public void hit(Entity entity) {
        // TODO: 29/03/2021 hit
    }

    public abstract boolean canHaveName(String name);

    public String getName() {
        return name;
    }

    public void setName(String name) throws IllegalArgumentException {
        if(!canHaveName(name))
            throw new IllegalArgumentException(MessageFormat.format("The given name \"{0}\"  can not be used", name));
        this.name = name;
    }

    public double getStrength() {
        return strength;
    }

    private void setStrength(double strength) {
        this.strength = strength;
    }

    public abstract double getStrengthOfHit();

    public void multiplyStrength(int multiplier) {
        if(multiplier != 0)
            setStrength(getStrength() * multiplier);
    }

    public void divideStrength(int denominator) {
        if(denominator != 0)
            setStrength(getStrength() / denominator);
    }

    public boolean isFighting() {
        return fighting;
    }

    public void setHitpoints(int hitpoints) {
        if(canHaveHitpoints(hitpoints)) {
            this.hitpoints = hitpoints;
        } else if(hitpoints <= 0) {
            this.hitpoints = 0;
        } else if(hitpoints > getMaxHitpoints()) {
            if(isFighting()) {
                this.hitpoints = getMaxHitpoints();
            } else {
                setHitpoints(getMaxHitpoints());
            }
        } else {
            this.hitpoints = Number.getClosestPrimeNumber(hitpoints);
        }
    }

    public int getMaxHitpoints() {
        return maxHitpoints;
    }

    public void setMaxHitpoints(int maxHitpoints) {
        if(isValidMaxHitpoints(maxHitpoints))
            this.maxHitpoints = maxHitpoints;
        else {
            this.maxHitpoints = 1;
        }
    }

    public abstract int getCapacity();

    public abstract int getProtectionHit();
    public int getProtection() {
        return protection;
    }

    public void setProtection(int protection) {
        this.protection = protection;
    }
}
