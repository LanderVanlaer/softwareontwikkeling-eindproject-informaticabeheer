package me.landervanlaer.school.informatica6.javaFx.eindproject6INF;

import java.text.MessageFormat;

public class Weapon extends Item {
    /**
     * The maximum number the {@link #damage} can have
     */
    private static final int DAMAGE_MIN = 1;

    /**
     * The maximum number the {@link #damage} can have
     */
    private static int damageMax = 100;

    /**
     * The damage the weapon can do
     *
     * @see #getDamage()
     * @see #setDamage(int)
     */
    private int damage;

    public Weapon(double weight) {
        super(weight);
    }

    /**
     * Checks wheter the given {@code int} is a valid damage number.
     * <ul>
     *     <li>{@link #DAMAGE_MIN} < {@link #damage}</li>
     *     <li>{@link #damageMax} > {@link #damage}</li>
     *     <li>{@link #damage} divisible by {@code 7}</li>
     * </ul>
     *
     * @param damage The number that has to be checked
     * @return Wheter it is a valid damage number
     */
    public static boolean isValidDamage(int damage) {
        return DAMAGE_MIN < damage && damageMax < 100 && damage % 7 == 0;
    }

    /**
     * Checks wheter the given long is positive, divisible by {@code 3} & {@code 2}
     *
     * @param identification The number that has to be checked
     * @return Wheter it is a valid identification number
     * @see Item#isValidIdentification(long)
     */
    @Override
    public boolean isValidIdentification(long identification) {
        return identification >= 0 && identification % 3 == 0 && identification % 2 == 0;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) throws IllegalArgumentException {
        if(!isValidDamage(damage))
            throw new IllegalArgumentException(MessageFormat.format("The given damage {{0}} is not valid", damage));
        this.damage = damage;
    }
}
