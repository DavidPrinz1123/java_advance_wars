package org.apps.advancewars.units;

public class MechanizedInfantry extends unit {
    public MechanizedInfantry(String team) {
        super("Mechanized Infantry", 100, 10, 3, "/org/apps/advancewars/images/troops/" + team +"/mech.png", team);
        this.minAttackRange = 1;
        this.maxAttackRange = 1;
    }

    //Wenn isGroundUnit is true, dann kann die Infantry nicht mehr auf die Berge
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
        return false;
    }

    @Override
    public int getWaterMovementCosts() {
        return Integer.MAX_VALUE;
    }

    @Override
    public int getPlainMovementCosts() {
        return 1;
    }

    @Override
    public int getWoodMovementCosts() {
        return 1;
    }

    @Override
    public int getMountainMovementCosts() {
        return 1;
    }

    @Override
    public double getAntiAirModifier() {
        return 1;
    }

    @Override
    public double getBattleCopterModifier() {
        return 0;
    }

    @Override
    public double getBomberModifier() {
        return 0;
    }

    @Override
    public double getFighterModifier() {
        return 0;
    }

    @Override
    public double getInfantryModifier() {
        return 1;
    }

    @Override
    public double getMechanizedInfantryModifier() {
        return 0.8;
    }

    @Override
    public double getMobileArtilleryModifier() {
        return 1.2;
    }

    @Override
    public double getTankModifier() {
        return 0.8;
    }
}