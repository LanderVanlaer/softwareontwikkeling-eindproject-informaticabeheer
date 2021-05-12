package me.landervanlaer.school.informatica6.javaFx.eindproject6INF;

import javafx.animation.AnimationTimer;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.javafx.Container;

public class GameLoop extends AnimationTimer {
    public final double FRAME = 0.01D;
    private double t = 0;
    private long startNanoTime;

    @Override
    public void handle(long now) {
        double t = ((now - startNanoTime) / 1000000000.0);
        if(t - getT() > FRAME) {
            setT(t);
//            System.out.println(Math.abs(t - getT() - FRAME)); //deviation, for testing purposes
            Container.getInstance().update();
            Container.getInstance().draw();
        }
    }

    public double getT() {
        return t;
    }

    public void setT(double t) {
        this.t = t;
    }

    public long getStartNanoTime() {
        return startNanoTime;
    }

    public void setStartNanoTime(long startNanoTime) {
        this.startNanoTime = startNanoTime;
    }
}