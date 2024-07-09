package org.apps.advancewars.units;

public class Bomber extends unit {
    public Bomber(String team) {
        super("Bomber", 100, 50, 7, "/org/apps/advancewars/images/troops/" + team + "/bomber.png", team,1,1,1,1,1,1,true,false,false,3);
    }

}
