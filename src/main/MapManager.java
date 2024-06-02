package main;

import main.maps.LittleIsland;
import main.maps.EonSprings;
import main.maps.PistonDam;

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
