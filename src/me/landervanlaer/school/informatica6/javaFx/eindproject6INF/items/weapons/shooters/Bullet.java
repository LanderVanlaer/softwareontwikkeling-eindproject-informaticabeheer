package me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.shooters;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import me.landervanlaer.math.Angle;
import me.landervanlaer.math.Coordinate;
import me.landervanlaer.math.Mover;
import me.landervanlaer.math.Vector;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.Game;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.GameLoop;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.entities.Bot;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.entities.Entity;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.entities.Player;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.javafx.Draw;

public class Bullet extends Mover {
    public static final long MAX_LIFE_TIME = 10L * GameLoop.ONE_SECOND_NANO;
    /**
     * @see <a href="https://howtodoinjava.com/java-examples/correctly-compare-float-double/">https://howtodoinjava.com/java-examples/correctly-compare-float-double/</a>
     */
    public static final double IS_INSIDE_TRIANGLE_THRESHOLD = 1;
    public static final double MASS = 1;
    private final int damage;
    private final long startTime;
    private boolean delete = false;

    public Bullet(int damage, Coordinate pos, Vector vel) {
        super(pos, vel, Bullet.MASS);
        this.damage = damage;
        this.startTime = System.nanoTime();
    }

    public static double getMASS() {
        return MASS;
    }

    /**
     * @see <a href="https://math.stackexchange.com/a/2035466">https://math.stackexchange.com/a/2035466</a>
     */
    public static boolean checkCollision(Coordinate prevPos, Coordinate currPos, Bot bot) {
        double ax = prevPos.getX() - bot.getPos().getX();
        double ay = prevPos.getY() - bot.getPos().getY();

        double bx = currPos.getX() - bot.getPos().getX();
        double by = currPos.getY() - bot.getPos().getY();

        double a = Math.pow(bx - ax, 2) + Math.pow(by - ay, 2);
        double b = 2 * (ax * (bx - ax) + ay * (by - ay));
        double c = Math.pow(ax, 2) + Math.pow(ay, 2) - Math.pow(bot.getRadius(), 2);

        double disc = Math.pow(b, 2) - 4 * a * c;

        if(disc <= 0) return false;

        double sqrtdisc = Math.sqrt(disc);
        double t1 = (-b + sqrtdisc) / (2 * a);
        double t2 = (-b - sqrtdisc) / (2 * a);

        return 0 < t1 && t1 < 1 || 0 < t2 && t2 < 1;
    }

    public static boolean checkCollision(Coordinate prevPos, Coordinate currPos, Player player) {
        final Angle left = new Angle(player.getAngle().getRadians());
        left.setDegrees(left.getDegrees(false) + 120);

        final Angle right = new Angle(player.getAngle().getRadians());
        right.setDegrees(right.getDegrees(false) - 120);


        Coordinate p1 = new Coordinate(player.getPos().getX() + Math.cos(player.getAngle().getRadians()) * player.getRadiusOfTriangle(), player.getPos().getY() + Math.sin(player.getAngle().getRadians()) * player.getRadiusOfTriangle());
        Coordinate p2 = new Coordinate(player.getPos().getX() + Math.cos(right.getRadians()) * player.getRadiusOfTriangle() / 2, player.getPos().getY() + Math.sin(right.getRadians()) * player.getRadiusOfTriangle() / 2);
        Coordinate p3 = new Coordinate(player.getPos().getX() + Math.cos(left.getRadians()) * player.getRadiusOfTriangle() / 2, player.getPos().getY() + Math.sin(left.getRadians()) * player.getRadiusOfTriangle() / 2);

        return isInsideTriangle(p1, p2, p3, prevPos) ||
                isInsideTriangle(p1, p2, p3, currPos) ||
                doLinesIntersect(prevPos, currPos, p1, p2) ||
                doLinesIntersect(prevPos, currPos, p2, p3) ||
                doLinesIntersect(prevPos, currPos, p3, p1);
    }

    public static boolean doLinesIntersect(Coordinate p1, Coordinate p2, Coordinate p3, Coordinate p4) {
        double a1 = (p1.getY() - p2.getY()) / (p1.getX() - p2.getX());
        double a2 = (p3.getY() - p4.getY()) / (p3.getX() - p4.getX());

        //parallel
        if(a1 == a2) return false;

        double b1 = p1.getY() - a1 * p1.getX();
        double b2 = p3.getY() - a2 * p3.getX();

        double x = (b2 - b1) / (a1 - a2);
        double y = a1 * x + b1;

        return isNumberBetween(x, p1.getX(), p2.getX())
                && isNumberBetween(x, p3.getX(), p4.getX())
                && isNumberBetween(y, p1.getY(), p2.getY())
                && isNumberBetween(y, p3.getY(), p4.getY());
    }

    public static boolean isNumberBetween(double n, double a, double b) {
        return Math.min(a, b) <= n && n <= Math.max(a, b);
    }

    /**
     * A utility function to calculate area of triangle formed by
     * (x1, y1), (x2, y2) and (x3, y3)
     *
     * @see <a href="https://www.dummies.com/education/math/algebra/finding-the-area-of-a-triangle-using-its-coordinates/">https://www.dummies.com/education/math/algebra/finding-the-area-of-a-triangle-using-its-coordinates/</a>
     */
    public static double areaOfTriangle(Coordinate c1, Coordinate c2, Coordinate c3) {
        return Math.abs((c1.getX() * c2.getY())
                + (c2.getX() * c3.getY())
                + (c3.getX() * c1.getY())
                - (c1.getX() * c3.getY())
                - (c2.getX() * c1.getY())
                - (c3.getX() * c2.getY())
        ) / 2D;
    }

    /* A function to check whether point P(x, y) lies inside the triangle formed
    by A(x1, y1), B(x2, y2) and C(x3, y3) */
    public static boolean isInsideTriangle(Coordinate c1, Coordinate c2, Coordinate c3, Coordinate p) {
        /* Calculate area of triangle ABC */
        double A = areaOfTriangle(c1, c2, c3);

        /* Calculate area of triangle PBC */
        double A1 = areaOfTriangle(p, c2, c3);

        /* Calculate area of triangle PAC */
        double A2 = areaOfTriangle(c1, p, c3);

        /* Calculate area of triangle PAB */
        double A3 = areaOfTriangle(c1, c2, p);

        /* Check if sum of A1, A2 and A3 is same as A */
//        return Math.abs(A -(A1 + A2 + A3)) < IS_INSIDE_TRIANGLE_THRESHOLD;
        return A == (A1 + A2 + A3);
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        Draw.line(gc, getPos(), new Coordinate(getPos().getX() + getVel().getX(), getPos().getY() + getVel().getY()));
    }

    @Override
    public void update() {
        if(!Game.getInstance().getPlayField().isBetweenBorders(getPos())) {
            stop();
            return;
        }

        final Coordinate prevPos = new Coordinate(getPos());
        super.update();

        for(Entity bot : Game.getInstance().getEntities()) {
            if(bot instanceof Bot)
                if(checkCollision(prevPos, getPos(), (Bot) bot)) {
                    bot.hit(this);
                }
        }

        final Player player = Game.getInstance().getPlayer();
        if(checkCollision(prevPos, getPos(), player)) {
            player.hit(this);
        }

        if(getStartTime() + MAX_LIFE_TIME < System.nanoTime()) {
            stop();
        }
    }

    public int getDamage() {
        return damage;
    }

    public void stop() {
        setDelete(true);
    }

    public long getStartTime() {
        return startTime;
    }

    public boolean isDelete() {
        return delete;
    }

    private void setDelete(boolean delete) {
        this.delete = delete;
    }
}
