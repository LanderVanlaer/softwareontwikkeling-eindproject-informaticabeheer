package me.landervanlaer.school.informatica6.javaFx.eindproject6INF.entities;

import me.landervanlaer.math.Coordinate;
import me.landervanlaer.math.Number;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.Game;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.Playfield;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.config.ConfigHandler;

public class BotFactory {
    public static Bot generateRandom() {
        final int hp = randomHp();
        return new Bot(hp, BotFactory.generateRandomCoordinateOnPlayfield(Game.getInstance().getPlayField()), BotFactory.hpToMass(hp));
    }

    public static int randomHp() {
        return Number.getRandom(ConfigHandler.getInt("entities.Bot.MIN_HP"), ConfigHandler.getInt("entities.Bot.MAX_HP"));
    }

    public static double hpToMass(int maxHp) {
        return maxHp * 1.2;
    }

    public static Coordinate generateRandomCoordinateOnPlayfield(Playfield playfield) {
        return new Coordinate(Number.getRandom(0, playfield.getWidth()), Number.getRandom(0, playfield.getHeight()));
    }
}
