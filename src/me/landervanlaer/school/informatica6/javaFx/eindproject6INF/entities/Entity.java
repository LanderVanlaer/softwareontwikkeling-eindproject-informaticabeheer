package me.landervanlaer.school.informatica6.javaFx.eindproject6INF.entities;

import me.landervanlaer.math.Number;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.AnchorPoint;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.Armor;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.Backpack;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.Item;

import java.text.MessageFormat;
import java.util.HashMap;

public abstract class Entity {
    public static final int DEFAULT_STRENGTH = 10;

    private final HashMap<AnchorPoint, Item> anchors;
    private String name;
    private double strength;
    private boolean fighting;
    private int hitpoints;
    private int maxHitpoints;
    private int protection;

    protected Entity(String name, int maxHitpoints, int protection) {
        if(!canHaveName(name))
            throw new IllegalArgumentException("The given name is not valid");
        if(maxHitpoints <= 0)
            throw new IllegalArgumentException("MaxHitpoints Cannot be less than or equal to 0");
        this.name = name;
        this.maxHitpoints = maxHitpoints;
        this.hitpoints = this.maxHitpoints;
        this.strength = DEFAULT_STRENGTH;
        this.fighting = false;
        this.protection = protection;
        anchors = new HashMap<>();
    }

    public static boolean isValidMaxHitpoints(int i) {
        return i > 0;
    }

    public static boolean isValidProtection(int i) {
        return i >= 0 && i < 20;

    }

    public HashMap<AnchorPoint, Item> getAnchors() {
        return anchors;
    }

    public boolean canHaveHitpoints(int i) {
        return i >= 0 && i <= getMaxHitpoints() && (isFighting() || Number.isPrimeNumber(i));
    }

    public boolean canHaveItem(Item item) {
        if(getAnchors().containsValue(item)) return true;

        if(item instanceof Armor) {
            // MAX 2 ARMORS
            return getAnchors().values().stream().filter(i -> i instanceof Armor).count() < 2;
        }

        return getRemainingCapacity() - item.getWeight() >= 0;
    }

    public int getRemainingCapacity() {
        return getCapacity() - getTotalCarryingWeight();
    }

    public int getTotalCarryingWeight() {
        return (int) getAnchors().values().stream().mapToDouble(Item::getWeight).sum();
    }

    public void throwItem(AnchorPoint anchorPoint) {
        final Item item = takeItem(anchorPoint);
        if(item != null) item.destroy();
    }

    public void swapItems(AnchorPoint a1, AnchorPoint a2) {
        final Item temp = getAnchors().put(a1, getAnchors().get(a2));
        getAnchors().put(a2, temp);
    }

    public Backpack getBackpack(){
        return (Backpack) getAnchors().values().stream().filter(item -> item instanceof Backpack).findFirst().orElse(null);
    }

    public void addItem(Item item) throws NullPointerException, IllegalArgumentException {
        if(item == null)
            throw new NullPointerException("Item can not be null, use Entity.throwItem(AnchorPoint) instead");
        if(!canHaveItem(item))
            throw new IllegalArgumentException("Can not hold item with id:" + item.getIdentification());

        item.setHolder(this);

        for(AnchorPoint a : AnchorPoint.values()) {
            if(getAnchors().get(a) == null) {
                putItem(item, a);
                return;
            };
        }

        final Backpack backpack = getBackpack();
        if(backpack != null && backpack.canAddItem(item)){
            backpack.addItem(item);
        }
    }

    public Item putItem(Item item, AnchorPoint anchorPoint) throws NullPointerException, IllegalArgumentException {
        if(item == null)
            throw new NullPointerException("Item can not be null, use Entity.throwItem(AnchorPoint) instead");
        if(!canHaveItem(item))
            throw new IllegalArgumentException("Can not hold item with id:" + item.getIdentification());

        item.setHolder(this);

        final Item prev = getAnchors().put(anchorPoint, item);

        if(prev != null) prev.setHolder(null);

        return prev;
    }

    public Item takeItem(AnchorPoint anchorPoint) {
        final Item item = getAnchors().get(anchorPoint);
        if(item != null)
            item.setHolder(null);
        return item;
    }

    public abstract void hit(Entity entity);

    public void heal() {
        setHitpoints((int) (getHitpoints() + ((double) (getMaxHitpoints() - getHitpoints()) * (Number.getRandom(0, 101) / 100D))));
    }

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

    public abstract boolean canHaveName(String name);

    public abstract double getStrengthOfHit();

    public void multiplyStrength(int multiplier) {
        if(multiplier != 0)
            setStrength(getStrength() * (double) multiplier);
    }

    public void divideStrength(int denominator) {
        if(denominator != 0)
            setStrength(getStrength() / (double) denominator);
    }

    public boolean isFighting() {
        return fighting;
    }

    public void setFighting(boolean fighting) {
        this.fighting = fighting;
        if(!fighting){
            setHitpoints(getHitpoints());
        }
    }

    public int getHitpoints() {
        return hitpoints;
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

    public void reduceHitpoints(int hitpoints) {
        if(hitpoints > 0)
            setHitpoints(getHitpoints() - hitpoints);
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

    public abstract int getProtectionForHit();

    public int getProtection() {
        return protection;
    }

    public void setProtection(int protection) {
        if(!isValidProtection(protection))
            throw new IllegalArgumentException();
        this.protection = protection;
    }
}
