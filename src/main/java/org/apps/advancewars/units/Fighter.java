package org.apps.advancewars.units;

public class Fighter extends unit {
    public Fighter() {
        super("Fighter", 100, 40, 9, "/org/apps/advancewars/images/troops/fighter.png");
    }
    @Override
    public boolean isAirUnit() {
        return true;
    }
}
