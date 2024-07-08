package org.apps.advancewars.units;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.apps.advancewars.terrain.Terrain;

import java.util.HashMap;
import java.util.Map;

public abstract class unit {
    protected String name;
    protected int health;
    protected int attackPower;
    protected int movementRange;
    protected ImageView imageView;
    protected int row;
    protected int col;
    protected String team;
    protected Map<String, Integer> movementCosts; // Movement costs for different terrain types
    protected boolean movedThisTurn;
    protected boolean attackedThisTurn;

    public unit(String name, int health, int attackPower, int movementRange, String imagePath, String team) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
        this.movementRange = movementRange;
        this.imageView = new ImageView(new Image(getClass().getResource(imagePath).toExternalForm()));
        this.movementCosts = new HashMap<>();
        this.team = team;
        try {
            this.imageView = new ImageView(new Image(getClass().getResource(imagePath).toExternalForm()));
        } catch (Exception e) {
            System.err.println("Error loading image: " + imagePath);
            e.printStackTrace();
            this.imageView = new ImageView(); // Provide a default ImageView or handle as needed
        }
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public int getMovementRange() {
        return movementRange;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public String getTeam() {
        return team;
    }

    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }
    public boolean getMovedThisTurn() {return movedThisTurn;}

    public boolean getAttackThisTurn() {return attackedThisTurn;}

    public void setAttackedThisTurn(boolean attackedThisTurn) {
        this.attackedThisTurn = attackedThisTurn;
    }
    public void setMovedThisTurn (boolean movedThisTurn) {
        this.movedThisTurn = movedThisTurn;
    }

    public void move(int newRow, int newCol) {
        if (!canMoveTo(newRow, newCol)) return;
        setPosition(newRow, newCol);
        movedThisTurn = true;
        // Additional logic to update the UI can be added here
    }

    public boolean canMoveTo(int newRow, int newCol) {
        int distance = Math.abs(newRow - row) + Math.abs(newCol - col);
        return distance <= movementRange;
    }

    abstract public boolean isGroundUnit();

    abstract public boolean isAirUnit();

    abstract public boolean canAttackGroundUnit();

    abstract public boolean canAttackAirUnit();

    public boolean isInfantry() {
        return false;
    }


    // Method to calculate movement cost based on terrain
    public int getMovementCost(Terrain terrain) {
        // Check if the unit has a specific movement cost for this terrain
        Integer cost = movementCosts.get(terrain.getName());
        // If not found, return the default movement cost
        return cost != null ? cost : 1;
    }


}
