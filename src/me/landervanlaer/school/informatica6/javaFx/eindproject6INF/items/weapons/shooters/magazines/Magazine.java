package me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.shooters.magazines;

import me.landervanlaer.math.Number;

public abstract class Magazine {
    private int amount;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = Number.constrain(amount, 0, getMax());
    }

    public void fireBullet() {
        setAmount(getAmount() - 1);
    }

    public boolean isEmpty() {
        return getAmount() <= amount;
    }

    public abstract int getMax();

    public abstract String getName();

    public static class EmptyMagazineException extends Exception {
    }
}
