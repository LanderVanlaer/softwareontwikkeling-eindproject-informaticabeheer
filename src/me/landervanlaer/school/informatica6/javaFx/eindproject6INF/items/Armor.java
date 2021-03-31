package me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items;

import me.landervanlaer.math.Number;

import java.text.MessageFormat;

public class Armor extends Item {
    public static final int PROTECTION_MIN = 1;
    public static final int PROTECTION_MAX = 100;
    /**
     * The protection that the armor gives
     *
     * @see #isValidProtection(int)
     */
    private int protection;

    public Armor(double weight, int protection) {
        super(weight);
        this.protection = protection;
    }

    /**
     * Checks wheter the given {@code int} is a valid damage number.
     * <ul>
     *     <li>{@link #PROTECTION_MIN} <= {@link #protection}</li>
     *     <li>{@link #PROTECTION_MAX} >= {@link #protection}</li>
     * </ul>
     *
     * @param protection The number that has to be checked
     * @return Wheter it is a valid damage number
     */
    public static boolean isValidProtection(int protection) {
        return protection >= PROTECTION_MIN && protection <= PROTECTION_MAX;
    }

    /**
     * Checks wheter the given long is positive and a prime
     *
     * @param identification The number that has to be checked
     * @return Wheter it is a valid identification number
     * @see Item#canHaveIdentification(long)
     */
    public static boolean isValidIdentification(long identification) {
        return Number.isPrimeNumber(identification);
    }

    @Override
    protected boolean canHaveIdentification(long identification) {
        return Armor.isValidIdentification(identification);
    }

    public int getProtection() {
        return protection;
    }

    public void setProtection(int protection) {
        if(!isValidProtection(protection))
            throw new IllegalArgumentException(MessageFormat.format("The given protection '{'{0}'}' is not valid", protection));
        this.protection = protection;
    }
}
