package me.landervanlaer.school.informatica6.javaFx.eindproject6INF.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import me.landervanlaer.math.Coordinate;
import me.landervanlaer.math.Number;
import me.landervanlaer.math.Vector;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.Game;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.*;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.Weapon;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.shooters.magazines.HeavyMagazine;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.shooters.magazines.LightMagazine;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.shooters.magazines.Magazine;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.shooters.magazines.MediumMagazine;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.javafx.Draw;

import java.util.List;

public class Bot extends Entity {
    public static final int MAX_HP = 200;
    public static final int MIN_HP = 50;
    public static final int HP_BAR_HEIGHT = 10;
    public static final int ARMOR_SPAWN_MIN = 10;
    private static final int DRAWING_WIDTH_MIN = 20;
    private static final int DRAWING_WIDTH_MAX = 100;
    private static final int BULLET_LOCATION_EXTRA_RADIUS = 10;
    public static int GO_TO_DEVIATION = 100;
    public static int RANDOM_LOCATION_MAX = 400;
    public static int VISION_LENGTH = 800;
    public static int ATTACK_GO_TO_SQUARE_WIDTH = 325;
    public static double MOVEMENT_SPEED = Entity.MOVEMENT_SPEED / 1.1;

    private Coordinate goTo;
    private Entity currentEnemy;

    public Bot(int maxHp, Coordinate pos, double mass) {
        super(maxHp, pos, mass);

        final double hpPercentageOfMax = (double) getHp() / MAX_HP;

        int randomAmountOfItems;
        int maxArmorProtection;
        int maxBackpackWeight;
        if(hpPercentageOfMax > .70) {
            setHand(WeaponFactory.AssaultRifleFactory.generateRandom());

            maxBackpackWeight = Number.getRandom(20, 70);
            setBackPack(new Backpack(maxBackpackWeight, Number.getRandom(2, 10)));

            randomAmountOfItems = Number.getRandom(4, 12);
            maxArmorProtection = Armor.PROTECTION_MAX;
        } else if(hpPercentageOfMax > .33) {
            setHand(WeaponFactory.SubmachineGunFactory.generateRandom());

            maxBackpackWeight = Number.getRandom(10, 50);
            setBackPack(new Backpack(maxBackpackWeight, Number.getRandom(2, 10)));

            randomAmountOfItems = Number.getRandom(2, 6);
            maxArmorProtection = (int) (Armor.PROTECTION_MAX * .6);
        } else {
            setHand(WeaponFactory.PistolFactory.generateRandom());

            maxBackpackWeight = Number.getRandom(5, 30);
            setBackPack(new Backpack(maxBackpackWeight, Number.getRandom(2, 10)));

            randomAmountOfItems = Number.getRandom(0, 4);
            maxArmorProtection = 20;
        }
        final Backpack backpack = getBackpack();
        for(int i = 0; i < randomAmountOfItems; i++) {
            try {
                backpack.addItem(ItemFactory.generateRandom(Number.getRandom(2, 10), Number.getRandom(ARMOR_SPAWN_MIN, maxArmorProtection), maxBackpackWeight));
            } catch(Backpack.MaxMassExceeded ignore) {
            }
        }
    }

    public static Coordinate generateRandomCoordinateAround(Coordinate pos) {
        final double devi = GO_TO_DEVIATION * 1.4;
        final double xMax = Game.getInstance().getPlayField().getWidth() - devi;
        final double yMax = Game.getInstance().getPlayField().getHeight() - devi;

        final Coordinate coordinate = new Coordinate(
                Number.getRandom(
                        (int) Number.constrain(pos.getX() - RANDOM_LOCATION_MAX, devi, xMax),
                        (int) Number.constrain(pos.getX() + RANDOM_LOCATION_MAX, devi, xMax)),
                Number.getRandom(
                        (int) Number.constrain(pos.getY() - RANDOM_LOCATION_MAX, devi, yMax),
                        (int) Number.constrain(pos.getY() + RANDOM_LOCATION_MAX, devi, yMax))
        );

        if(coordinate.getDistanceBetween(pos) < GO_TO_DEVIATION)
            return generateRandomCoordinateAround(pos);

        return coordinate;
    }

    public static Coordinate intersectionOfLines(Coordinate p1, Coordinate p2, Coordinate p3, Coordinate p4) {
        final double a1 = (p1.getY() - p2.getY()) / (p1.getX() - p2.getX());
        final double a2 = (p3.getY() - p4.getY()) / (p3.getX() - p4.getX());

        //parallel
        if(a1 == a2) return null;

        final double b1 = p1.getY() - a1 * p1.getX();
        final double b2 = p3.getY() - a2 * p3.getX();

        if(Double.isInfinite(a1)) return new Coordinate(p1.getX(), a2 * p1.getX() + b2);
        if(Double.isInfinite(a2)) return new Coordinate(p3.getX(), a1 * p3.getX() + b1);

        final double x = (b2 - b1) / (a1 - a2);
        final double y = a1 * x + b1;

        return new Coordinate(x, y);
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.SANDYBROWN);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);

        final double d = getDiameter();
        Draw.fillCircle(gc, getPos(), d);

        Draw.hpBar(gc, new Coordinate(getPos().getX(), getPos().getY() - d / 2D - HP_BAR_HEIGHT), getHpPercentage(), d, HP_BAR_HEIGHT);

//        gc.setStroke(Color.BLACK);
//        gc.setLineWidth(2);
//        Draw.line(gc, getPos(), getGoTo());
//
//        gc.setStroke(Color.RED);
//        final Entity entity = getClosestEnemy();
//        if(entity == null) return;
//        Draw.line(gc, getPos(), entity.getPos());

//        final Coordinate leftTop = new Coordinate(getPos());
//        leftTop.add(new Vector(-ATTACK_GO_TO_SQUARE_WIDTH, -ATTACK_GO_TO_SQUARE_WIDTH));
//        final Coordinate leftBottom = new Coordinate(getPos());
//        leftBottom.add(new Vector(-ATTACK_GO_TO_SQUARE_WIDTH, +ATTACK_GO_TO_SQUARE_WIDTH));
//        final Coordinate rightTop = new Coordinate(getPos());
//        rightTop.add(new Vector(+ATTACK_GO_TO_SQUARE_WIDTH, -ATTACK_GO_TO_SQUARE_WIDTH));
//        final Coordinate rightBottom = new Coordinate(getPos());
//        rightBottom.add(new Vector(+ATTACK_GO_TO_SQUARE_WIDTH, +ATTACK_GO_TO_SQUARE_WIDTH));
//        gc.setStroke(Color.GRAY);
//        gc.setLineWidth(1);
//        Draw.line(gc, leftBottom, leftTop);
//        Draw.line(gc, leftTop, rightTop);
//        Draw.line(gc, rightTop, rightBottom);
//        Draw.line(gc, rightBottom, leftBottom);
    }

    public double getDiameter() {
        return Number.constrain(getMass() / 2D, Bot.DRAWING_WIDTH_MIN, Bot.DRAWING_WIDTH_MAX);
    }

    public double getRadius() {
        return getDiameter() / 2D;
    }

    @Override
    public void update() {
        setCurrentEnemy(getClosestEnemy());
        if(getGoTo() == null || getGoTo().getDistanceBetween(getPos()) <= GO_TO_DEVIATION)
            setGoTo(Bot.generateRandomCoordinateAround(getPos()));

        useAttack();

        final Vector goToVector = new Vector(getPos(), getGoTo());
        goToVector.setMag(Bot.MOVEMENT_SPEED);

        applyForce(goToVector);

        super.update();
    }

    @Override
    public void die() {
        getBackpack().drop(getPos());
        getBackpack().drop();
        getHand().drop();
    }

    @Override
    public Magazine getNewMagazine(MagazineChecker magazineChecker) {
        Magazine[] magazines = new Magazine[]{new LightMagazine(), new MediumMagazine(), new HeavyMagazine()};
        for(Magazine magazine : magazines) {
            if(magazineChecker.canHaveMagazine(magazine))
                return magazine;
        }
        throw new InternalError("The bot is not able to make a new Magazine that meets the given requirements");
    }

    @Override
    public void useAttack() {
        final Entity entity = getCurrentEnemy();
        if(entity == null) return;

        final Coordinate leftTop = new Coordinate(entity.getPos());
        leftTop.add(new Vector(-ATTACK_GO_TO_SQUARE_WIDTH, -ATTACK_GO_TO_SQUARE_WIDTH));

        final Coordinate leftBottom = new Coordinate(entity.getPos());
        leftBottom.add(new Vector(-ATTACK_GO_TO_SQUARE_WIDTH, +ATTACK_GO_TO_SQUARE_WIDTH));

        final Coordinate rightTop = new Coordinate(entity.getPos());
        rightTop.add(new Vector(+ATTACK_GO_TO_SQUARE_WIDTH, -ATTACK_GO_TO_SQUARE_WIDTH));

        final Coordinate rightBottom = new Coordinate(entity.getPos());
        rightBottom.add(new Vector(+ATTACK_GO_TO_SQUARE_WIDTH, +ATTACK_GO_TO_SQUARE_WIDTH));

        final Coordinate[] intersections = new Coordinate[]{
                intersectionOfLines(entity.getPos(), getPos(), leftBottom, leftTop),
                intersectionOfLines(entity.getPos(), getPos(), leftTop, rightTop),
                intersectionOfLines(entity.getPos(), getPos(), rightTop, rightBottom),
                intersectionOfLines(entity.getPos(), getPos(), rightBottom, leftBottom),
        };

        Coordinate best = null;
        for(Coordinate next : intersections) {
            if(best == null) {
                best = next;
                continue;
            }
            if(next == null) continue;
            if(best.getDistanceBetween(getPos()) > next.getDistanceBetween(getPos())) {
                best = next;
            }

        }
        setGoTo(best);

        shootTo(entity.getPos());
    }

    @Override
    public double getbulletStartLocationRadius() {
        return getRadius() + BULLET_LOCATION_EXTRA_RADIUS;
    }

    public void shootTo(Coordinate coordinate) {
        if(coordinate == null) return;

        final Item hand = getHand();

        if(hand instanceof Weapon) ((Weapon) hand).attack(coordinate);
    }

    public Entity getClosestEnemy() {
        final Player player = Game.getInstance().getPlayer();
        final List<Entity> entities = Game.getInstance().getEntities();
        final Entity closestEntity = entities.stream().filter(entity -> entity != this && entity.getPos().getDistanceBetween(getPos()) <= VISION_LENGTH).min((o1, o2) -> (int) o1.getPos().getDistanceBetween(o2.getPos())).orElse(null);

        if(player.getPos().getDistanceBetween(getPos()) <= VISION_LENGTH) {
            return closestEntity == null || player.getPos().getDistanceBetween(getPos()) < closestEntity.getPos().getDistanceBetween(getPos()) ? player : closestEntity;
        }
        return closestEntity;
    }

    public Coordinate getGoTo() {
        return goTo;
    }

    public void setGoTo(Coordinate goTo) {
        this.goTo = goTo;
    }

    public Entity getCurrentEnemy() {
        return currentEnemy;
    }

    public void setCurrentEnemy(Entity currentEnemy) {
        this.currentEnemy = currentEnemy;
    }
}
