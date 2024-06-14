package org.apps.advancewars.units;

public class MechanizedInfantry extends unit {
    public MechanizedInfantry(String team) {
        super("Infantry", 100, 10, 3, "/org/apps/advancewars/images/troops/" + team +"/mech.png", team);
    }

    //Wenn isGroundUnit is true, dann kann die Infantry nicht mehr auf die Berge
    @Override
    public boolean isGroundUnit() {
        return false;
    }

    @Override
    public boolean isInfantry() {
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
        return false;
    }
}