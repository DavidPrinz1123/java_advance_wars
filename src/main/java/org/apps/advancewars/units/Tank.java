package org.apps.advancewars.units;

public class Tank extends unit {
    public Tank(String team) {
        super("Tank", 150, 25, 6, "/org/apps/advancewars/images/troops/" + team + "/tank.png", team);
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