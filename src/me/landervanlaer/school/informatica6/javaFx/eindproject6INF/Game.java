package me.landervanlaer.school.informatica6.javaFx.eindproject6INF;

import me.landervanlaer.math.Number;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.entities.Entity;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.entities.Hero;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.entities.Monster;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.Item;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.Weapon;

import java.text.MessageFormat;

public class Game {
    public static void main(String[] args) {
        Hero hero = new Hero("Hero O'Conner", 250);
        hero.addItem(new Weapon(21, 15));
        printItems(hero);

        Monster monster = new Monster("Monster Gibson", 50, 1, true);
        printItems(monster);

        System.out.println("-------------------");

        Entity winner = fight(hero, monster);
        System.out.println("Winner: " + winner.getName());
        printItems(winner);
    }

    public static void printItems(Entity entity) {
        System.out.println("Total weight carrying: " + entity.getTotalCarryingWeight());
        System.out.println("Total HP: " + entity.getHitpoints());
        System.out.println("Items:");
        for(Item item : entity.getAnchors().values()) {
            System.out.println(MessageFormat.format("\t{0} : {1}", item.getClass().getSimpleName(), item.getIdentification()));
        }
    }

    public static Entity fight(Entity entity0, Entity entity1) {
        int i = Number.getRandom(0, 2);
        entity0.setFighting(true);
        entity1.setFighting(true);
        while(entity0.getHitpoints() > 0 && entity1.getHitpoints() > 0) {
            switch(i) {
                case 0 -> {
                    System.out.println(MessageFormat.format("{0} is going to hit {1}\n\t{0}\t->\t{2}hp\n\t{1}\t->\t{3}hp", entity0.getName(), entity1.getName(), entity0.getHitpoints(), entity1.getHitpoints()));
                    entity0.hit(entity1);
                    i = 1;
                    System.out.println(MessageFormat.format("{0} hit {1}\n\t{0}\t->\t{2}hp\n\t{1}\t->\t{3}hp", entity0.getName(), entity1.getName(), entity0.getHitpoints(), entity1.getHitpoints()));
                }
                case 1 -> {
                    System.out.println(MessageFormat.format("{1} is going to hit {0}\n\t{0}\t->\t{2}hp\n\t{1}\t->\t{3}hp", entity0.getName(), entity1.getName(), entity0.getHitpoints(), entity1.getHitpoints()));
                    entity1.hit(entity0);
                    i = 0;
                    System.out.println(MessageFormat.format("{1} hit {0}\n\t{0}\t->\t{2}hp\n\t{1}\t->\t{3}hp", entity0.getName(), entity1.getName(), entity0.getHitpoints(), entity1.getHitpoints()));
                }
            }
            System.out.println();
        }
        entity0.setFighting(false);
        entity1.setFighting(false);
        return entity0.getHitpoints() > 0 ? entity0 : entity1;
    }
}
