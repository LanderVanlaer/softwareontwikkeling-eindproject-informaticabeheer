package me.landervanlaer.school.informatica6.javaFx.eindproject6INF;

import java.util.Random;

abstract public class Item {
    protected static final Random RANDOM = new Random();

    /**
     * A unique number that identifies the element
     */
    private final long identification;
    /**
     * The weight of the element
     */
    private final double weight;
    /**
     * The hero who owns the item
     */
    private Held holder;

    public Item(double weight) {
        if(weight < 0) weight = 0;

        this.weight = weight;
        this.identification = generateIdentification();
    }

    /**
     * Generates a random number for the {@link #identification}
     *
     * @return A random identification number
     * @see #identification
     * @see #isValidIdentification(long)
     */
    protected long generateIdentification() {
        long i;
        do i = RANDOM.nextLong(); while(!isValidIdentification(i));
        return i;
    }

    /**
     * Tells whether the given number is a valid weight
     *
     * @param weight The number that has to be checked
     * @return Wheter it is a valid weight for a weapon
     * @see #weight
     */
    public boolean isValidWeight(long weight) {
        return weight >= 0;
    }

    /**
     * Tells whether the given number is a valid identification number
     *
     * @param identification The number that has to be checked
     * @return Wheter it is a valid identification number
     * @see #identification
     * @see #generateIdentification()
     */
    abstract public boolean isValidIdentification(long identification);

    public long getIdentification() {
        return identification;
    }

    public double getWeight() {
        return weight;
    }

    public Held getHolder() {
        return holder;
    }

    public void setHolder(Held holder) {
        this.holder = holder;
    }
}
