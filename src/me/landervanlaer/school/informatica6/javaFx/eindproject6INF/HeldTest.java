package me.landervanlaer.school.informatica6.javaFx.eindproject6INF;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HeldTest {
    private Held held100;

    @Test // meerdere @BeforeClass ging niet
    public void isValidProtection_Legal() {
        assertTrue(Held.isValidProtection(16));
    }

    @Test
    public void isValidProtection_Illegal0() {
        assertFalse(Held.isValidProtection(-1));
    }

    @Test
    public void isValidProtection_Illegal1() {
        assertFalse(Held.isValidProtection(20));
    }

    @Test
    public void isValidMaxHitpoints_Legal() {
        assertTrue(Held.isValidMaxHitpoints(100));
    }

    @Test
    public void isValidMaxHitpoints_Illegal() {
        assertFalse(Held.isValidMaxHitpoints(0));
    }

    @Test
    public void getCapacityByStrength0() {
        assertEquals(0, Held.getCapacityByStrength(0), 0);
    }

    @Test
    public void getCapacityByStrength1() {
        assertEquals(20, Held.getCapacityByStrength(2), 0);
    }

    @Test
    public void getCapacityByStrength2() {
        assertEquals(115, Held.getCapacityByStrength(11), 0);
    }

    @Test
    public void getCapacityByStrength3() {
        assertEquals(175, Held.getCapacityByStrength(14), 0);
    }

    @Test
    public void getCapacityByStrength4() {
        assertEquals(400, Held.getCapacityByStrength(20), 0);
    }

    @Test
    public void getCapacityByStrength5() {
        assertEquals(460, Held.getCapacityByStrength(21), 0);
    }

    @Test
    public void getCapacityByStrength6() {
        assertEquals(3200, Held.getCapacityByStrength(35), 0);
    }

    @Before
    public void setUp() {
        held100 = new Held("Held", 100);
    }

    @Test
    public void setName_Legal() {
        held100.setName("Hel'd Frans");
        assertEquals("Hel'd Frans", held100.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setName_Illegal0() {
        held100.setName("el'd Frans");
    }

    @Test(expected = IllegalArgumentException.class)
    public void setName_Illegal1() {
        held100.setName("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void setName_Illegal2() {
        held100.setName("F");
    }

    @Test(expected = IllegalArgumentException.class)
    public void setName_Illegal3() {
        held100.setName("Fan ' ka ' il ' oel");
    }

    @Test
    public void reduceHitpoints0() {
        held100.reduceHitpoints(90);
        assertEquals(7, held100.getHitpoints());
    }

    @Test
    public void reduceHitpoints1() {
        held100.setFighting(true);
        held100.reduceHitpoints(90);
        assertEquals(10, held100.getHitpoints());
    }

    @Test
    public void multiplyStrength() {
        held100.multiplyStrength(2);
        assertEquals(Held.STRENGTH_AVARAGE * 2D, held100.getStrength(), .01);
    }

    @Test
    public void divideStrength() {
        held100.divideStrength(2);
        assertEquals(Held.STRENGTH_AVARAGE / 2D, held100.getStrength(), .01);
    }

    @Test
    public void setProtection_Legal0() {
        held100.setProtection(1);
        assertEquals(1, held100.getProtection());
    }

    @Test
    public void setProtection_Legal1() {
        held100.setProtection(19);
        assertEquals(19, held100.getProtection());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setProtection_Illegal0() {
        held100.setProtection(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setProtection_Illegal1() {
        held100.setProtection(0);
    }
}