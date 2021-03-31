package me.landervanlaer.school.informatica6.javaFx.eindproject6INF.entities;

import me.landervanlaer.math.Number;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HeroTest {
    private Hero hero100;

    @Test // meerdere @BeforeClass ging niet
    public void isValidProtection_Legal() {
        assertTrue(Hero.isValidProtection(16));
    }

    @Test
    public void isValidProtection_Illegal0() {
        assertFalse(Hero.isValidProtection(-1));
    }

    @Test
    public void isValidProtection_Illegal1() {
        assertFalse(Hero.isValidProtection(21));
    }

    @Test
    public void isValidMaxHitpoints_Legal() {
        assertTrue(Hero.isValidMaxHitpoints(100));
    }

    @Test
    public void isValidMaxHitpoints_Illegal() {
        assertFalse(Hero.isValidMaxHitpoints(0));
    }

    @Test
    public void getCapacityByStrength0() {
        assertEquals(0, Hero.getCapacityByStrength(0), 0);
    }

    @Test
    public void getCapacityByStrength1() {
        assertEquals(20, Hero.getCapacityByStrength(2), 0);
    }

    @Test
    public void getCapacityByStrength2() {
        assertEquals(115, Hero.getCapacityByStrength(11), 0);
    }

    @Test
    public void getCapacityByStrength3() {
        assertEquals(175, Hero.getCapacityByStrength(14), 0);
    }

    @Test
    public void getCapacityByStrength4() {
        assertEquals(400, Hero.getCapacityByStrength(20), 0);
    }

    @Test
    public void getCapacityByStrength5() {
        assertEquals(460, Hero.getCapacityByStrength(21), 0);
    }

    @Test
    public void getCapacityByStrength6() {
        assertEquals(3200, Hero.getCapacityByStrength(35), 0);
    }

    @Before
    public void setUp() {
        hero100 = new Hero("Held", 100);
    }

    @Test
    public void setName_Legal() {
        hero100.setName("Hel'd Frans");
        assertEquals("Hel'd Frans", hero100.getName());
    }

    @Test
    public void constructor0_Legal() {
        Hero hero = new Hero("Oliver Mc'Donald", 100);
        assertEquals("Oliver Mc'Donald", hero.getName());
        assertEquals(100, hero.getHitpoints());
    }

    @Test
    public void constructor1_Legal() {
        Hero hero = new Hero("Martin Akashiya", 10);
        assertEquals("Martin Akashiya", hero.getName());
        assertEquals(10, hero.getHitpoints());
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor0_Illegal() {
        new Hero("ivan Adarsh", 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor1_Illegal() {
        new Hero("Ivan Adarsh", -1);
    }

    @Test
    public void getClosestPrimeNumber0() {
        assertEquals(7, Number.getClosestPrimeNumber(10));
    }

    @Test
    public void getClosestPrimeNumber1() {
        assertEquals(1, Number.getClosestPrimeNumber(1));
    }

    @Test
    public void getClosestPrimeNumber2() {
        assertEquals(83, Number.getClosestPrimeNumber(88));
    }

    @Test
    public void getClosestPrimeNumber3() {
        assertEquals(73, Number.getClosestPrimeNumber(73));
    }

    @Test
    public void hit0() {
        Hero hero = new Hero("Held", 100);
        hero.setProtection(0);
        hero.setFighting(true);
        hero100.multiplyStrength(2); //20
        hero100.hit(hero);
        assertEquals(95, hero.getHitpoints(), .1);
    }

    @Test
    public void hit1() {
        Hero hero = new Hero("Held", 100);
        hero.setProtection(0);
        hero.setFighting(false);
        hero100.multiplyStrength(2); //20
        hero100.hit(hero);
        assertEquals(Number.getClosestPrimeNumber(95), hero.getHitpoints(), .1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setName_Illegal0() {
        hero100.setName("el'd Frans");
    }

    @Test(expected = IllegalArgumentException.class)
    public void setName_Illegal1() {
        hero100.setName("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void setName_Illegal2() {
        hero100.setName("F");
    }

    @Test(expected = IllegalArgumentException.class)
    public void setName_Illegal3() {
        hero100.setName("Fan ' ka ' il ' oel");
    }

    @Test
    public void reduceHitpoints0() {
        hero100.reduceHitpoints(90);
        assertEquals(7, hero100.getHitpoints());
    }

    @Test
    public void reduceHitpoints1() {
        hero100.setFighting(true);
        hero100.reduceHitpoints(90);
        assertEquals(10, hero100.getHitpoints());
    }

    @Test
    public void multiplyStrength() {
        hero100.multiplyStrength(2);
        assertEquals(20, hero100.getStrength(), .01);
    }

    @Test
    public void divideStrength() {
        hero100.divideStrength(2);
        assertEquals(5, hero100.getStrength(), .01);
    }

    @Test
    public void setProtection_Legal0() {
        hero100.setProtection(1);
        assertEquals(1, hero100.getProtection());
    }

    @Test
    public void setProtection_Legal1() {
        hero100.setProtection(19);
        assertEquals(19, hero100.getProtection());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setProtection_Illegal0() {
        hero100.setProtection(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setProtection_Illegal1() {
        hero100.setProtection(21);
    }
}