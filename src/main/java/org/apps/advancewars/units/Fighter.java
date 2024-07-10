package org.apps.advancewars.units;

public class Fighter extends unit {
    public Fighter(String team) {
        super("Fighter", 100, 40, 9, "/org/apps/advancewars/images/troops/" + team + "/fighter.png", team);
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
        return false;
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
        return 0;
    }

    @Override
    public double getBattleCopterModifier() {
        return 1.5;
    }

    @Override
    public double getBomberModifier() {
        return 1.5;
    }

    @Override
    public double getFighterModifier() {
        return 1;
    }

    @Override
    public double getInfantryModifier() {
        return 0;
    }

    @Override
    public double getMechanizedInfantryModifier() {
        return 0;
    }

    @Override
    public double getMobileArtilleryModifier() {
        return 0;
    }

    @Override
    public double getTankModifier() {
        return 0;
    }
}

