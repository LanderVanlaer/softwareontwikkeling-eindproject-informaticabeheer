package me.landervanlaer.school.informatica6.javaFx.eindproject6INF.entities;

import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.AnchorPoint;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.Armor;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.Item;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.Weapon;

public class Hero extends Entity {
    public static final int DEFAULT_PROTECTION = 10;
    private final static int[] DEFAULT_CAPACITY = new int[]{115, 130, 150, 175, 200, 230, 260, 300, 350, 400};
    private final static AnchorPoint[] ATTACK_ANCHORPOINTS = new AnchorPoint[]{AnchorPoint.LEFT_HAND, AnchorPoint.RIGHT_HAND};
    private final static AnchorPoint ARMOR_ANCHORPOINT = AnchorPoint.BODY;

    public Hero(String name, int maxHitpoints) {
        super(name, maxHitpoints, DEFAULT_PROTECTION);
        if(!name.matches("[A-Z][a-zA-Z ]+'?[a-zA-Z ]*'?[a-zA-Z ]*")) throw new IllegalArgumentException("Invalid name");
    }

    public Hero() {
        this("Hero", 100);
    }

    public static int getCapacityByStrength(double strength) {
        if(strength < 1)
            return 0;
        else if(strength < 11)
            return (int) (strength * 10);
        else if(strength < 21)
            return DEFAULT_CAPACITY[(int) (strength - 11)];
        else
            return getCapacityByStrength(strength - 10D) * 4;
    }

    public static boolean isValidName(String name) {
        return name.matches("[A-Z][a-zA-Z ]+'?[a-zA-Z ]*'?[a-zA-Z ]*");
    }

    @Override
    public boolean canHaveName(String name) {
        return isValidName(name);
    }

    @Override
    public double getStrengthOfHit() {
        double total = getStrength();
        for(AnchorPoint point : ATTACK_ANCHORPOINTS) {
            Item item = getAnchors().get(point);
            if(item instanceof Weapon)
                total += ((Weapon) item).getDamage();
        }
        return total;
    }

    @Override
    public int getCapacity() {
        return getCapacityByStrength(getStrength());
    }

    @Override
    public int getProtectionForHit() {
        final Item item = getAnchors().get(ARMOR_ANCHORPOINT);
        return item instanceof Armor ? ((Armor) item).getProtection() + getProtection() : getProtection();
    }
}
