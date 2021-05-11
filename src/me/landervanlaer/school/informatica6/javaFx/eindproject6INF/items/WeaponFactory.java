package me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items;

import com.sun.jdi.InternalException;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.Weapon;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.shooters.assaultRifles.Ak47;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.shooters.assaultRifles.AssaultRifle;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.shooters.assaultRifles.M4;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.shooters.pistols.DesertEagle;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.shooters.pistols.Glock;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.shooters.pistols.Pistol;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.shooters.submachineGuns.P90;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.shooters.submachineGuns.SubmachineGun;
import me.landervanlaer.school.informatica6.javaFx.eindproject6INF.items.weapons.shooters.submachineGuns.Uzi;

import static me.landervanlaer.school.informatica6.javaFx.eindproject6INF.Factory.getRandomValueOf;

public class WeaponFactory {
    public static Weapon generateRandom() {
        switch(getRandomEnumValue()) {
            case PISTOL -> {
                return PistolFactory.generateRandom();
            }
            case SUBMACHINE_GUN -> {
                return SubmachineGunFactory.generateRandom();
            }
            case ASSAULT_RIFLE -> {
                return AssaultRifleFactory.generateRandom();
            }
            default -> {
                throw new InternalException();
            }
        }
    }

    public static WeaponType getRandomEnumValue() {
        return getRandomValueOf(WeaponType.values());
    }

    public static class PistolFactory {
        public static Pistol generateRandom() {
            switch(getRandomEnumValue()) {
                case GLOCK -> {
                    return new Glock();
                }
                case DESERT_EAGLE -> {
                    return new DesertEagle();
                }
                default -> throw new InternalException();
            }
        }

        public static WeaponType.Pistol getRandomEnumValue() {
            return getRandomValueOf(WeaponType.Pistol.values());
        }
    }

    public static class SubmachineGunFactory {
        public static SubmachineGun generateRandom() {
            switch(getRandomEnumValue()) {
                case P90 -> {
                    return new P90();
                }
                case UZI -> {
                    return new Uzi();
                }
                default -> throw new InternalException();
            }
        }

        public static WeaponType.SubmachineGun getRandomEnumValue() {
            return getRandomValueOf(WeaponType.SubmachineGun.values());
        }
    }

    public static class AssaultRifleFactory {
        public static AssaultRifle generateRandom() {
            switch(getRandomEnumValue()) {
                case AK_47 -> {
                    return new Ak47();
                }
                case M4 -> {
                    return new M4();
                }
                default -> throw new InternalException();
            }
        }

        public static WeaponType.AssaultRifle getRandomEnumValue() {
            return getRandomValueOf(WeaponType.AssaultRifle.values());
        }
    }
}
