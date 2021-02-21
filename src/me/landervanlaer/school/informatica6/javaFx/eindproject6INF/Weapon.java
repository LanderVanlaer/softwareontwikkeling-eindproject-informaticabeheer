package me.landervanlaer.school.informatica6.javaFx.eindproject6INF;

import java.util.Random;

public class Weapon {
    private static final Random RANDOM = new Random();
    /**
     * A number that is positive, even and divisible by 3. To identify the weapon.
     */
    private final long identification;

    /**
     * A number that is positive. To determine the weight of the weapon.
     */
    private final double weight;

    public Weapon(double weight) {
        this.weight = weight;
        this.identification = generateIdentification();
    }

    /**
     * Generates a random number for the {@link #identification}
     *
     * @return A random identification
     * @see #identification
     * @see #generateIdentification()
     */
    public static long generateIdentification() {
        long i;
        do i = RANDOM.nextLong(); while(!isValidIdentification(i));
        return i;
    }

    /**
     * Tells whether the given number is positive and whether the number is even and divisible by 3
     *
     * @param identification The number that has to be checked
     * @return Wheter it is a valid identification number
     * @see #identification
     * @see #generateIdentification()
     */
    public static boolean isValidIdentification(long identification) {
        return identification >= 0 && identification % 3 == 0 && identification % 2 == 0;
    }

    /**
     * Tells whether the given number is positive
     *
     * @param weight The number that has to be checked
     * @return Wheter it is a valid weight for a weapon
     * @see #weight
     */
    public static boolean isValidWeight(long weight) {
        return weight >= 0;
    }

    public long getIdentification() {
        return identification;
    }

    public double getWeight() {
        return weight;
    }
}
