package org.apps.advancewars.units;

public class AntiAir extends unit {
    public AntiAir(String team) {
        super("Anti Air", 100, 35, 6, "/org/apps/advancewars/images/troops/" + team +"/antiair.png", team);
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
        return 1;
    }

    @Override
    public int getMountainMovementCosts() {
        return Integer.MAX_VALUE;
    }

    @Override
    public double getAntiAirModifier() {
        return 0.8;
    }

    @Override
    public double getBattleCopterModifier() {
        return 1.5;
    }

    @Override
    public double getBomberModifier() {
        return 1;
    }

    @Override
    public double getFighterModifier() {
        return 1;
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
        return 1;
    }

    @Override
    public double getTankModifier() {
        return 0.8;
    }
}
