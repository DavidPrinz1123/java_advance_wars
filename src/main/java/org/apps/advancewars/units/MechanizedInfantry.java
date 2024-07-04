package org.apps.advancewars.units;

public class MechanizedInfantry extends unit {
    public MechanizedInfantry(String team) {
        super("Infantry", 100, 10, 3, "/org/apps/advancewars/images/troops/" + team +"/mech.png", team,false,1,1,100,1,1,1);
    }

    @Override
    public boolean isInfantry() {
        return true;
    }
}