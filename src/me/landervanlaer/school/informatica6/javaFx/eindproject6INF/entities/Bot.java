package me.landervanlaer.school.informatica6.javaFx.eindproject6INF.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import me.landervanlaer.math.Coordinate;
import me.landervanlaer.math.Number;
import me.landervanlaer.math.Vector;
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
    }

    public static Coordinate generateRandomCoordinateAround(Coordinate pos) {
        return new Coordinate(
                pos.getX() + (Math.random() < .5 ? -1 : 1) * Number.getRandom(GO_TO_DEVIATION * 2, RANDOM_LOCATION_MAX),
                pos.getY() + (Math.random() < .5 ? -1 : 1) * Number.getRandom(GO_TO_DEVIATION * 2, RANDOM_LOCATION_MAX)
        );
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
