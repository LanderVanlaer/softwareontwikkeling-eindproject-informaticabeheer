package me.landervanlaer.school.informatica6.javaFx.eindproject6INF;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class WeaponTest {

    @Test
    public void generateIdentification() {
        final List<Long> longs = new LinkedList<>();

        while(longs.size() < 1000) {
            final long l = Weapon.generateIdentification();
            assertTrue(Weapon.isValidIdentification(l));
            assertFalse(longs.contains(l));
            longs.add(l);
        }
    }

    @Test
    public void isValidIdentification_LEGALCASE0() {
        assertTrue(Weapon.isValidIdentification(90));
    }

    @Test
    public void isValidIdentification_LEGALCASE1() {
        assertTrue(Weapon.isValidIdentification(6));
    }

    @Test
    public void isValidIdentification_LEGALCASE2() {
        assertTrue(Weapon.isValidIdentification(9223372036854775806L));
    }

    @Test
    public void isValidIdentification_ILLEGAL_NEGATIVE() {
        assertFalse(Weapon.isValidIdentification(-9223372036854775806L));
    }

    @Test
    public void isValidIdentification_ILLEGAL_POSITIVE_DIVISIBLE3() {
        assertFalse(Weapon.isValidIdentification(99));
    }


    @Test
    public void isValidIdentification_ILLEGAL_POSITIVE_DIVISIBLE2() {
        assertFalse(Weapon.isValidIdentification(100));
    }
}
