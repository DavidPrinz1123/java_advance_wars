package org.apps.advancewars.units;

public class Bomber extends unit {
    public Bomber(String team) {
        super("Bomber", 100, 50, 7, "/org/apps/advancewars/images/troops/" + team + "/bomber.png", team);
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
        return false;
    }

    @Override
    public int getWaterMovementCosts() {
        return 1;
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
        return 1.5;
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
        return 1.5;
    }

    @Override
    public double getMechanizedInfantryModifier() {
        return 1.5;
    }

    @Override
    public double getMobileArtilleryModifier() {
        return 1.5;
    }

    @Override
    public double getTankModifier() {
        return 1.5;
    }
}
