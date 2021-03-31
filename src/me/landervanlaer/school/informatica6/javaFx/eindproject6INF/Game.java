package me.landervanlaer.school.informatica6.javaFx.eindproject6INF;

import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.entities.Hero;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.entities.Monster;

public class Game {
    public static void main(String[] args) {
        Hero hero = new Hero("Hero O'Conner", 100);
        System.out.println(hero.getTotalCarryingWeight());

        Monster monster = new Monster("Monster Gibson", 50, 5);
        System.out.println(monster.getTotalCarryingWeight());
    }
}
