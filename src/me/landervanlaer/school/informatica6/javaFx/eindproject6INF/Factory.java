package me.landervanlaer.school.informatica6.javaFx.eindproject6INF;

import com.sun.jdi.InternalException;
import me.landervanlaer.math.Number;

import java.util.Arrays;

public abstract class Factory {
    public static <T> T getRandomValueOf(T[] values) {
        return values[Number.getRandom(0, values.length)];
    }

    public static <T extends Factory.WeightHolder> T getRandomByWeightValueOf(T[] values) {
        int total = Arrays.stream(values).mapToInt(Factory.WeightHolder::getWeight).sum();

        for(T value : values) {
            total -= value.getWeight();
            if(total <= 0)
                return value;
        }
        throw new InternalException();
    }


    public interface WeightHolder {
        int getWeight();
    }
}
