package me.landervanlaer.school.informatica6.javaFx.eindproject6INF;

import me.landervanlaer.math.Number;

public abstract class Factory {
    public static <T> T getRandomValueOf(T[] values) {
        return values[Number.getRandom(0, values.length)];
    }
}
