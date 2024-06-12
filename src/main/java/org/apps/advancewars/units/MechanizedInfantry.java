package org.apps.advancewars.units;

public class MechanizedInfantry extends unit {
    public MechanizedInfantry(String team) {
        super("Infantry", 100, 10, 3, "/org/apps/advancewars/images/troops/" + team +"/mech.png", team);
    }


    @Override
    public boolean isGroundUnit() {
        return true;
    }

    @Override
    public boolean isAirUnit() {
        return false;
    }
}