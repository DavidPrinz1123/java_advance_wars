package org.apps.advancewars.units;

public class Fighter extends unit {
    public Fighter(String team) {
        super("Fighter", 100, 40, 9, "/org/apps/advancewars/images/troops/" + team + "/fighter.png", team);
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
        return false;
    }

    @Override
    public boolean canAttackAirUnit() {
        return true;
    }
}

