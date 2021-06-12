package me.landervanlaer.school.informatica6.javaFx.eindproject6INF.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import me.landervanlaer.math.Coordinate;
import me.landervanlaer.math.Number;
import me.landervanlaer.math.Vector;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.Game;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.Armor;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.Backpack;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.ItemFactory;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.WeaponFactory;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.javafx.Draw;

public class Bot extends Entity {
    public static final int MAX_HP = 200;
    public static final int MIN_HP = 50;
    public static final int HP_BAR_HEIGHT = 10;
    private static final int DRAWING_WIDTH_MIN = 20;
    private static final int DRAWING_WIDTH_MAX = 100;
    public static int GO_TO_DEVIATION = 50;
    public static int RANDOM_LOCATION_MAX = 400;
    public static double MOVEMENT_SPEED = Entity.MOVEMENT_SPEED / 4D;

    private Coordinate goTo;

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
                backpack.addItem(ItemFactory.generateRandom(Number.getRandom(2, 10), Number.getRandom(Armor.PROTECTION_MIN, maxArmorProtection), maxBackpackWeight));
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

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.SANDYBROWN);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);

        final double d = getDiameter();
        Draw.fillCircle(gc, getPos(), d);

        Draw.hpBar(gc, new Coordinate(getPos().getX(), getPos().getY() - d / 2D - HP_BAR_HEIGHT), getHpPercentage(), d, HP_BAR_HEIGHT);
    }

    public double getDiameter() {
        return Number.constrain(getMass() / 2D, Bot.DRAWING_WIDTH_MIN, Bot.DRAWING_WIDTH_MAX);
    }

    public double getRadius() {
        return getDiameter() / 2D;
    }

    @Override
    public void update() {
        if(getGoTo() == null || getGoTo().getDistanceBetween(getPos()) <= GO_TO_DEVIATION)
            setGoTo(Bot.generateRandomCoordinateAround(getPos()));

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
    public void useAttack() {
        // TODO: 27/04/2021
    }

    public Coordinate getGoTo() {
        return goTo;
    }

    public void setGoTo(Coordinate goTo) {
        this.goTo = goTo;
    }
}
