package me.landervanlaer.school.informatica6.javaFx.eindproject6INF.entities;

import me.landervanlaer.math.Angle;
import me.landervanlaer.math.Coordinate;
import me.landervanlaer.math.Mover;
import me.landervanlaer.math.Number;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.AnchorPoint;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.Armor;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.Backpack;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.Item;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.shooters.Bullet;

import java.util.EnumMap;

abstract public class Entity extends Mover {
    public static double MOVEMENT_SPEED = 5;
    public static double ROTATION_SPEED = 2;
    public static double MOVEMENT_SPEED_MAX = 9;
    public static double DEFAULT_BACKPACK_MASS_MAX = 10;
    public static double DEFAULT_BACKPACK_MASS = 2;
    private final int maxHp;
    private final EnumMap<AnchorPoint, Item> anchorPoints;
    private Angle angle;
    private int hp;

    public Entity(int maxHp, Coordinate pos, double mass) {
        super(pos, mass);
        if(maxHp <= 0) throw new IllegalArgumentException("MaxHp can not be 0 or negative");
        this.maxHp = maxHp;
        this.hp = this.maxHp;
        this.anchorPoints = new EnumMap<>(AnchorPoint.class);
        final Backpack backpack = new Backpack(DEFAULT_BACKPACK_MASS_MAX, DEFAULT_BACKPACK_MASS);
        backpack.setHolder(this);
        this.anchorPoints.put(AnchorPoint.BACK, backpack);
        this.angle = new Angle();
    }

    @Override
    public void update() {
        //check if there is a Backpack
        if(!(getAnchorPoints().get(AnchorPoint.BACK) instanceof Backpack)) {
            final Backpack backpack = new Backpack(DEFAULT_BACKPACK_MASS_MAX, DEFAULT_BACKPACK_MASS);
            getAnchorPoints().put(AnchorPoint.BACK, backpack);
        }

        super.update();
    }

    @Override
    public double getMass() {
        double mass = super.getMass();
        mass += getAnchorPoints().values().stream().mapToDouble(Item::getMass).sum();
        return mass;
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

    public void addItemToBackpack(Item item) throws NoBackpack {
        final Item backpack = getAnchorPoints().get(AnchorPoint.BACK);

        if(!(backpack instanceof Backpack))
            throw new NoBackpack();

        ((Backpack) backpack).addItem(item);
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

    public double getHpPercentage() {
        return (double) getHp() / (double) getMaxHp();
    }

    public abstract void useAttack();

    public Angle getAngle() {
        return angle;
    }

    public void setAngle(Angle angle) {
        this.angle = angle;
    }

    public static class NoBackpack extends Exception {
    }
}
