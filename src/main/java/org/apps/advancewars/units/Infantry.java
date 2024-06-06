package org.apps.advancewars.units;

public class Infantry extends unit {
    public Infantry() {
        super("Infantry", 100, 10, 3, "/org/apps/advancewars/images/troops/infantry.png");
    }

    @Override
    public boolean isInfantry() {
        return true;
    }
}
