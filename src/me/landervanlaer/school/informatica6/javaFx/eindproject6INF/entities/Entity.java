package me.landervanlaer.school.informatica6.javaFx.eindproject6INF.entities;

import me.landervanlaer.math.Coordinate;
import me.landervanlaer.math.Mover;
import me.landervanlaer.math.Number;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.AnchorPoint;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.Armor;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.Item;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.shooters.Bullet;

import java.util.EnumMap;

abstract public class Entity extends Mover {
    private final int maxHp;
    private final EnumMap<AnchorPoint, Item> anchorPoints;
    private int hp;

    public Entity(int maxHp, Coordinate pos, double mass) {
        super(pos, mass);
        if(maxHp <= 0) throw new IllegalArgumentException("MaxHp can not be 0 or negative");
        this.maxHp = maxHp;
        this.hp = this.maxHp;
        this.anchorPoints = new EnumMap<>(AnchorPoint.class);
    }

    public void hit(Bullet bullet) {
        bullet.stop();

        int damage = bullet.getDamage();

        final Item bodyItem = getAnchorPoints().get(AnchorPoint.BODY);
        if(bodyItem instanceof Armor) {
            Armor armor = (Armor) bodyItem;
            damage = armor.reduceProtection(damage);

            if(damage > 0) {
                armor.setHolder(null);
                this.getAnchorPoints().remove(AnchorPoint.BODY);
            }
        }

        reduceHp(damage);
    }

    public void reduceHp(int hp) {
        if(hp > 0)
            setHp(getHp() - hp);
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

    public EnumMap<AnchorPoint, Item> getAnchorPoints() {
        return anchorPoints;
    }

    public void heal(int hp) {
        if(hp > 0)
            this.setHp(getHp() + hp);
    }

    public boolean isDead() {
        return getHp() <= 0;
    }

    public abstract void useAttack();
}
