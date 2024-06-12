package org.apps.advancewars.units;

public class BattleCopter extends unit {
    public BattleCopter(String team) {
        super("BattleCopter", 100, 20, 6, "/org/apps/advancewars/images/troops/" + team + "/battlecopter.png", team);
    }

    @Override
    public boolean isGroundUnit() {
        return false;
    }

    @Override
    public boolean isAirUnit() {
        return true;
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
