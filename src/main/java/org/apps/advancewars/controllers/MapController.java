package org.apps.advancewars.controllers;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import org.apps.advancewars.maps.EonSprings;
import org.apps.advancewars.maps.LittleIsland;
import org.apps.advancewars.maps.PistonDam;
import org.apps.advancewars.terrain.Terrain;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class MapController {
    private final int TILE_SIZE = 48; // Base size of each tile
    private GridPane gameGridPane;
    private Map<String, Terrain> terrainMap;
    private String currentMapName;

    public MapController(GridPane gameGridPane) {
        this.gameGridPane = gameGridPane;
        initializeTerrainMap();
    }

    private void initializeTerrainMap() {
        terrainMap = new HashMap<>();
        // Base terrains
        terrainMap.put("grass", new Terrain("grass", "grass.png", true, true, true, false));
        terrainMap.put("water", new Terrain("water", "water.png", false, true, false, false));
        terrainMap.put("wood", new Terrain("wood", "wood.png", true, true, true, true));
        terrainMap.put("mountain", new Terrain("mountain", "mountain.png", false, true, true, true));

        // Additional terrains using grass logic
        terrainMap.put("street_h", new Terrain("street_h", "street_h.png", true, true, true, false));
        terrainMap.put("little_village", new Terrain("little_village", "little_village.png", true, true, true, false));
        terrainMap.put("bridge_h", new Terrain("bridge_h", "bridge_h.png", true, true, true, false));
        terrainMap.put("bridge_v", new Terrain("bridge_v", "bridge_v.png", true, true, true, false));
        terrainMap.put("mountain_water", new Terrain("mountain_water", "mountain_water.png", false, true, true, true));
        terrainMap.put("street_v", new Terrain("street_v", "street_v.png", true, true, true, false));
        terrainMap.put("turn_left_down", new Terrain("turn_left_down", "turn_left_down.png", true, true, true, false));
        terrainMap.put("turn_left_up", new Terrain("turn_left_up", "turn_left_up.png", true, true, true, false));
        terrainMap.put("turn_right_down", new Terrain("turn_right_down", "turn_right_down.png", true, true, true, false));
        terrainMap.put("turn_right_up", new Terrain("turn_right_up", "turn_right_up.png", true, true, true, false));
    }

    public void setMapLayout(String mapName) {
        currentMapName = mapName;
        String[][] layout = getMapLayout(mapName);

        for (int row = 0; row < layout.length; row++) {
            for (int col = 0; col < layout[row].length; col++) {
                String terrainName = layout[row][col].replace(" ", "_").toLowerCase();
                Terrain terrain = terrainMap.getOrDefault(terrainName, terrainMap.get("grass"));
                ImageView imageView = new ImageView(getImageForTerrain(terrain));
                imageView.setFitWidth(TILE_SIZE);
                imageView.setFitHeight(TILE_SIZE);
                gameGridPane.add(imageView, col, row);

            }
        }
    }

    private Image getImageForTerrain(Terrain terrain) {
        String imagePath = "/org/apps/advancewars/images/map/" + terrain.getImageFileName();
        URL imageUrl = getClass().getResource(imagePath);
        if (imageUrl == null) {
            System.err.println("Image not found for terrain: " + terrain.getName() + " with path: " + imagePath);
            return new Image(getClass().getResource("/org/apps/advancewars/images/map/default.png").toExternalForm());  // Fallback to a default image
        }
        return new Image(imageUrl.toExternalForm());
    }

    public String[][] getMapLayout(String mapName) {
        switch (mapName) {
            case "EonSprings":
                return EonSprings.LAYOUT;
            case "LittleIsland":
                return LittleIsland.LAYOUT;
            case "PistonDam":
                return PistonDam.LAYOUT;
            default:
                return new String[0][0];
        }
    }

    public int getTileSize() {
        return TILE_SIZE;
    }

    public Terrain getTerrainAt(int row, int col) {
        String[][] layout = getMapLayout(currentMapName);
        String terrainName = layout[row][col].replace(" ", "_").toLowerCase();
        return terrainMap.getOrDefault(terrainName, terrainMap.get("grass"));
    }
}
