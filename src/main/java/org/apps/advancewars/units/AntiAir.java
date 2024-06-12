package org.apps.advancewars.units;

public class AntiAir extends unit {
    public AntiAir(String team) {
        super("Anti-Air", 100, 35, 6, "/org/apps/advancewars/images/troops/" + team +"/antiair.png", team);
    }

    @Override
    public boolean isGroundUnit() {
        return true;
    }

    @Override
    public boolean isAirUnit() {
        return false;
    }

    @Override
    public boolean canAttackGroundUnit() {
        return true;
    }

    @Override
    public boolean canAttackAirUnit() {
        return true;
    }
}
