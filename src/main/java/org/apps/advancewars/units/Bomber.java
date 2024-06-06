package org.apps.advancewars.units;

public class Bomber extends unit {
    public Bomber() {
        super("Bomber", 100, 50, 7, "/org/apps/advancewars/images/bomber.png");
    }
    @Override
    public boolean isAirUnit() {
        return true;
    }
}
