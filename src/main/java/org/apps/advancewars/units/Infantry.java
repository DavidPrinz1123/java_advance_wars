package org.apps.advancewars.units;

public class Infantry extends unit {
    public Infantry(String team) {
        super("Infantry", 100, 10, 3, "/org/apps/advancewars/images/troops/" + team + "/infantry.png", team,false,1,1,100,2,1,1);
    }

    @Override
    public boolean isInfantry() {
        return true;
    }
}

