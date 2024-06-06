package org.apps.advancewars.units;

public class MechanizedInfantry extends unit {
    public MechanizedInfantry() {
        super("Infantry", 100, 10, 3, "/org/apps/advancewars/images/troops/mech.png");
    }

    @Override
    public boolean isInfantry() {
        return true;
    }
}