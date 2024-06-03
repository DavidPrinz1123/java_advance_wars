package org.apps.java_advance_wars;

import org.apps.java_advance_wars.maps.LittleIsland;
import org.apps.java_advance_wars.maps.EonSprings;
import org.apps.java_advance_wars.maps.PistonDam;

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

