package org.apps.advancewars.units;

public class Infantry extends unit {
    public Infantry(String team) {
        super("Infantry", 10, 10, 3, "/org/apps/advancewars/images/troops/" + team + "/infantry.png", team);
        this.minAttackRange = 1;
        this.maxAttackRange = 1;
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
        return 1;
    }

    @Override
    public int getMountainMovementCosts() {
        return 2;
    }

    @Override
    public double getAntiAirModifier() {
        return 0.5;
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
        return 0.8;
    }

    @Override
    public double getMechanizedInfantryModifier() {
        return 0.8;
    }

    @Override
    public double getMobileArtilleryModifier() {
        return 0.8;
    }

    @Override
    public double getTankModifier() {
        return 0.5;
    }
}
