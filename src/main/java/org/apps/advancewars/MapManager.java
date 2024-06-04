package org.apps.advancewars;

import org.apps.advancewars.maps.EonSprings;
import org.apps.advancewars.maps.PistonDam;
import org.apps.advancewars.maps.LittleIsland;

public class MapManager {
    private String[][] currentMap;

    public MapManager(int mapNumber) {
        switch (mapNumber) {
            case 1:
                currentMap = LittleIsland.LAYOUT;
                break;
            case 2:
                currentMap = EonSprings.LAYOUT;
                break;
            case 3:
                currentMap = PistonDam.LAYOUT;
                break;
            default:
                currentMap = LittleIsland.LAYOUT;
        }
    }

    public String[][] getCurrentMap() {
        return currentMap;
    }
}

