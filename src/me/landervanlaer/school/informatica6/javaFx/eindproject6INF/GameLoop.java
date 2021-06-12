package me.landervanlaer.school.informatica6.javaFx.eindproject6INF;

import javafx.animation.AnimationTimer;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.javafx.Container;

public class GameLoop extends AnimationTimer {
    public static final int ONE_SECOND_NANO = 1_000_000_000;
    private long prevNanoTime = 0;
    private long nowNanoTime = 0;
    private long startNanoTime;

    @Override
    public void handle(long now) {
        //LIMIT TO 360 FPS
        if(now - getNowNanoTime() < 2_777_777.777777777777777777777)
            return;

        setPrevNanoTime(getNowNanoTime());
        setNowNanoTime(now);
        Container.getInstance().update();
        Container.getInstance().draw();
    }

    @Override
    public void start() {
        super.start();
        setStartNanoTime(System.nanoTime());
        setPrevNanoTime(System.nanoTime());
    }

    public int getFramesPerSecond() {
        return (int) (1 / getFrameTimeSeconds());
    }

    /**
     * Returns the time of the new frame in nanoseconds
     *
     * @return The frame time in nanoseconds
     * @see #getFrameTimeSeconds()
     */
    public int getFrameTime() {
        return (int) (getNowNanoTime() - getPrevNanoTime());
    }

    /**
     * Returns the time of the new frame in seconds
     *
     * @return The Frame time in seconds
     * @see #getFrameTime()
     * @see #ONE_SECOND_NANO
     */
    public double getFrameTimeSeconds() {
        return (double) getFrameTime() / (double) ONE_SECOND_NANO;
    }

    public long getNowNanoTime() {
        return nowNanoTime;
    }

    private void setNowNanoTime(long nowNanoTime) {
        this.nowNanoTime = nowNanoTime;
    }

    public long getPrevNanoTime() {
        return prevNanoTime;
    }

    private void setPrevNanoTime(long prevNanoTime) {
        this.prevNanoTime = prevNanoTime;
    }

    public long getStartNanoTime() {
        return startNanoTime;
    }

    private void setStartNanoTime(long startNanoTime) {
        this.startNanoTime = startNanoTime;
    }
}
