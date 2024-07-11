package org.apps.advancewars.units;

public class Tank extends unit {
    public Tank(String team) {
        super("Tank", 150, 25, 6, "/org/apps/advancewars/images/troops/" + team + "/tank.png", team);
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
        return true;
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
        return 2;
    }

    @Override
    public int getMountainMovementCosts() {
        return Integer.MAX_VALUE;
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
        return 1;
    }
}