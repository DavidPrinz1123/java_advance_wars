package org.apps.advancewars.units;

public class BattleCopter extends unit {
    public BattleCopter() {
        super("BattleCopter", 100, 20, 5, "/org/apps/advancewars/images/battle_copter.png");
    }

    @Override
    public boolean isAirUnit() {
        return true;
    }
}
