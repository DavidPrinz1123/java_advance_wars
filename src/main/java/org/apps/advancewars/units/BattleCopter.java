package org.apps.advancewars.units;

public class BattleCopter extends unit {
    public BattleCopter(String team) {
        super("BattleCopter", 100, 20, 6, "/org/apps/advancewars/images/troops/" + team + "/battlecopter.png", team);
        this.minAttackRange = 1;
        this.maxAttackRange = 1;
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
        return true;
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
        return 0.8;
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
        return 1.2;
    }

    @Override
    public double getMechanizedInfantryModifier() {
        return 1.2;
    }

    @Override
    public double getMobileArtilleryModifier() {
        return 1;
    }

    @Override
    public double getTankModifier() {
        return 0.8;
    }
}
