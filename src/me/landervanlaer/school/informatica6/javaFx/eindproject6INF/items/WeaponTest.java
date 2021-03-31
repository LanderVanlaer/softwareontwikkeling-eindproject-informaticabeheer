package me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class WeaponTest {
    private Weapon weapon100;

    @Before
    public void setUp() {
        Weapon.setDamageMax(100);
        this.weapon100 = new Weapon(98, 10);
    }

    @Test
    public void generateIdentification() {
        final List<Long> longs = new LinkedList<>();

        while(longs.size() < 1000) {
            final long l = weapon100.generateIdentification();
            assertTrue(weapon100.canHaveIdentification(l));
            assertFalse(longs.contains(l));
            longs.add(l);
        }
    }

    @Test
    public void isValidIdentification_LEGALCASE0() {
        assertTrue(weapon100.canHaveIdentification(90));
    }

    @Test
    public void isValidIdentification_LEGALCASE1() {
        assertTrue(weapon100.canHaveIdentification(6));
    }

    @Test
    public void isValidIdentification_LEGALCASE2() {
        assertTrue(weapon100.canHaveIdentification(9223372036854775806L));
    }

    @Test
    public void isValidIdentification_ILLEGAL_NEGATIVE() {
        assertFalse(weapon100.canHaveIdentification(-9223372036854775806L));
    }

    @Test
    public void isValidIdentification_ILLEGAL_POSITIVE_DIVISIBLE3() {
        assertFalse(weapon100.canHaveIdentification(99));
    }


    @Test
    public void isValidIdentification_ILLEGAL_POSITIVE_DIVISIBLE2() {
        assertFalse(weapon100.canHaveIdentification(100));
    }

    @Test
    public void isValidWeight_LEGAL0() {
        assertTrue(weapon100.isValidWeight(10));
    }

    @Test
    public void isValidWeight_LEGAL1() {
        assertTrue(weapon100.isValidWeight(10));
    }

    @Test
    public void isValidWeight_ILLEGAL0() {
        assertFalse(weapon100.isValidWeight(-5));
    }

    @Test
    public void isValidWeight_ILLEGAL1() {
        assertFalse(weapon100.isValidWeight(-8));
    }

    @Test
    public void isValidDamage_LEGAL0() {
        assertTrue(Weapon.isValidDamage(7));
    }

    @Test
    public void isValidDamage_LEGAL1() {
        assertTrue(Weapon.isValidDamage(84));
    }

    @Test
    public void isValidDamage_ILLEGAL0() {
        assertFalse(Weapon.isValidDamage(64));
    }

    @Test
    public void isValidDamage_ILLEGAL1() {
        assertFalse(Weapon.isValidDamage(-8));
    }

    @Test
    public void isValidDamage_ILLEGAL2() {
        assertFalse(Weapon.isValidDamage(777));
    }
}
