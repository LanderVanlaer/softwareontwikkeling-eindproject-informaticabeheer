package me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items;

import me.landervanlaer.math.Number;

import java.text.MessageFormat;

public class Armor extends Item {
    public static final int PROTECTION_MIN = 1;
    public static final int PROTECTION_MAX = 100;

    private int protection;

    public Armor(int protection, double weight) {
        super(weight);
        this.protection = protection;
    }

    public static boolean isValidProtection(int protection) {
        return protection >= PROTECTION_MIN && protection <= PROTECTION_MAX;
    }

    public int getProtection() {
        return protection;
    }

    public void setProtection(int protection) throws IllegalArgumentException {
        if(!isValidProtection(protection))
            throw new IllegalArgumentException(MessageFormat.format("The given protection '{'{0}'}' is not valid", protection));
        this.protection = protection;
    }

    public int reduceProtection(int i) throws IllegalArgumentException {
        final int pro = i - getProtection();

        setProtection(getProtection() - i);

        return Number.constrain(pro, 0, Integer.MAX_VALUE);
    }
}
