package org.apps.advancewars.units;

public class MobileArtillery extends unit {
    public MobileArtillery(String team) {
        super("Mobile Artillery", 90, 30, 5, "/org/apps/advancewars/images/troops/" + team + "/artillery.png", team);
    }
}
