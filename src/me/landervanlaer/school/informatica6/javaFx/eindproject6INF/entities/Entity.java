package me.landervanlaer.school.informatica6.javaFx.eindproject6INF.entities;

import me.landervanlaer.math.Coordinate;
import me.landervanlaer.math.Mover;
import me.landervanlaer.math.Number;

abstract public class Entity extends Mover {
    private final int maxHp;
    private int hp;

    public Entity(int maxHp, Coordinate pos, double mass) {
        super(pos, mass);
        if(maxHp <= 0) throw new IllegalArgumentException("MaxHp can not be 0 or negative");
        this.maxHp = maxHp;
        this.hp = this.maxHp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = Number.constrain(hp, 0, getMaxHp());
    }
}
