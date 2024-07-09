package org.apps.advancewars.units;

public class AntiAir extends unit {
    public AntiAir(String team) {
        super("Anti-Air", 100, 35, 6, "/org/apps/advancewars/images/troops/" + team +"/antiair.png", team,1,1,100,100,1,1,true,true,true,1);
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
