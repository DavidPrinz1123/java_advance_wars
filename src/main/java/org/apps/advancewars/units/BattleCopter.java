package org.apps.advancewars.units;

public class BattleCopter extends unit {
    public BattleCopter() {
        super("BattleCopter", 100, 20, 6, "/org/apps/advancewars/images/troops/battle_copter.png");
    }

    @Override
    public boolean isAirUnit() {
        return true;
    }
}
