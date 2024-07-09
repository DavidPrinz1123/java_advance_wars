package org.apps.advancewars.terrain;
import java.util.Map;
import java.util.HashMap;


public class Terrain {
    private final String name;
    private final String imageFileName;
    private final boolean passableByGroundUnits;
    private final boolean passableByAirUnits;
    private final boolean passableByInfantry;
    private final boolean providesCover;
    private final Map<String, Integer> movementCosts; // Movement costs for each unit type


    public Terrain(String name, String imageFileName, boolean passableByGroundUnits, boolean passableByAirUnits, boolean passableByInfantry, boolean providesCover) {
        this.name = name;
        this.imageFileName = imageFileName;
        this.passableByGroundUnits = passableByGroundUnits;
        this.passableByAirUnits = passableByAirUnits;
        this.passableByInfantry = passableByInfantry;
        this.providesCover = providesCover;
        this.movementCosts = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public boolean isPassableByGroundUnits() {
        return passableByGroundUnits;
    }

    public boolean isPassableByAirUnits() {
        return passableByAirUnits;
    }

    public boolean isPassableByInfantry() {
        return passableByInfantry;
    }

    public boolean providesCover() {
        return providesCover;
    }




}