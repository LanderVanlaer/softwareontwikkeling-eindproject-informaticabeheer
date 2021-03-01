package me.landervanlaer.school.informatica6.javaFx.eindproject6INF;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ArmorTest {
    private Armor armor50_20;

    @Before
    public void setUp() {
        this.armor50_20 = new Armor(50, 20);
    }

    @Test
    public void isValidProtection_LEGAL() {
        assertTrue(Armor.isValidProtection(10));
    }

    @Test
    public void isValidProtection_ILLEGAL0() {
        assertFalse(Armor.isValidProtection(-2));
    }

    @Test
    public void isValidProtection_ILLEGAL1() {
        assertFalse(Armor.isValidProtection(999));
    }

    @Test
    public void isValidIdentification_LEGAL0() {
        assertTrue(Armor.isValidIdentification(7919L));
    }

    @Test
    public void isValidIdentification_LEGAL1() {
        assertTrue(Armor.isValidIdentification(11L));
    }

    @Test
    public void isValidIdentification_ILLEGAL0() {
        assertFalse(Armor.isValidIdentification(10000000022L));
    }

    @Test
    public void isValidIdentification_ILLEGAL1() {
        assertFalse(Armor.isValidIdentification(10L));
    }

    @Test
    public void getProtection() {
    }

    @Test
    public void setProtection() {
    }
}