package org.apps.advancewars.units;

public class Tank extends unit {
    public Tank(String team) {
        super("Tank", 150, 25, 6, "/org/apps/advancewars/images/troops/" + team + "/tank.png", team,2,1,100,100,1,1,true,true,false,1);
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