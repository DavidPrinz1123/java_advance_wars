package org.apps.advancewars.units;

public class Bomber extends unit {
    public Bomber(String team) {
        super("Bomber", 100, 50, 7, "/org/apps/advancewars/images/troops/" + team + "/bomber.png", team);
    }

    @Override
    public boolean isGroundUnit() {
        return false;
    }

    @Override
    public boolean isAirUnit() {
        return true;
    }
}
