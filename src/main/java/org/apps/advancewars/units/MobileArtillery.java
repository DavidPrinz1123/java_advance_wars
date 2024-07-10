package org.apps.advancewars.units;

public class MobileArtillery extends unit {
    public MobileArtillery(String team) {
        super("Mobile Artillery", 90, 30, 5, "/org/apps/advancewars/images/troops/" + team + "/artillery.png", team);
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
        return 3;
    }

    @Override
    public int getMountainMovementCosts() {
        return Integer.MAX_VALUE;
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
        return 1.5;
    }

    @Override
    public double getMechanizedInfantryModifier() {
        return 1.5;
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
