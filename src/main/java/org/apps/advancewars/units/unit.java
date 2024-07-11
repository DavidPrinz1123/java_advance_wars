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
    protected int minAttackRange = 1; // Default attack range
    protected int maxAttackRange = 1; // Default attack range
    protected boolean attackAir = false;
    protected boolean attackMiddle = false;
    protected boolean attackGround = false;
    protected int movementRange;
    protected ImageView imageView;
    protected int row;
    protected int col;

    protected boolean movementBlocked = false;
    protected boolean attackBlocked = false;
    protected String team;
    protected Map<String, Integer> movementCosts;

    public unit(String name, int health, int attackPower, int movementRange, String imagePath, String team) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
        this.movementRange = movementRange;
        this.imageView = new ImageView(new Image(getClass().getResource(imagePath).toExternalForm()));
        this.movementCosts = new HashMap<>();
        this.team = team;
    }

    public String getName() {
        return name;
    }

    public int getMinAttackRange() {
        return minAttackRange;
    }

    public int getMaxAttackRange() {
        return maxAttackRange;
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

    public void setMovementBlocked(boolean blocked) {
        this.movementBlocked = blocked;
    }

    public boolean getMovementBlocked() {
        return movementBlocked;
    }

    public void setAttackBlocked(boolean attackBlocked) {
        this.attackBlocked = attackBlocked;
    }

    public boolean getAttackBlocked() {
        return attackBlocked;
    }

    public void move(int newRow, int newCol) {
        setPosition(newRow, newCol);
    }

    public boolean canMoveTo(int newRow, int newCol, Terrain terrain) {
        int distance = Math.abs(newRow - row) + Math.abs(newCol - col);
        int movementCosts = getMovementCost(terrain);
        int newDistance = distance + movementCosts - 1;
        return newDistance <= movementRange;
    }

    public boolean canAttack(unit enemy, int targetRow, int targetCol) {
        int distance = Math.abs(targetRow - row) + Math.abs(targetCol - col);
        boolean canAttack = distance >= getMinAttackRange() && distance <= getMaxAttackRange() && !getAttackBlocked();
        System.out.println("Checking if can attack: " + canAttack + " (distance: " + distance + ")");
        return canAttack;
    }

    public int getMovementCost(Terrain terrain) {
        switch (terrain.getName()) {
            case "water":
                return getWaterMovementCosts();
            case "mountain":
                return getMountainMovementCosts();
            case "wood":
                return getWoodMovementCosts();
            default:
                return getPlainMovementCosts();
        }
    }

    public void reset() {
        this.movementBlocked = false;
        this.attackBlocked = false;
    }

    abstract public boolean isGroundUnit();

    abstract public boolean isAirUnit();

    abstract public boolean canAttackGroundUnit();

    abstract public boolean canAttackAirUnit();

    abstract public int getWaterMovementCosts();

    abstract public int getPlainMovementCosts();

    abstract public int getWoodMovementCosts();

    abstract public int getMountainMovementCosts();

    abstract public double getAntiAirModifier();

    abstract public double getBattleCopterModifier();

    abstract public double getBomberModifier();

    abstract public double getFighterModifier();

    abstract public double getInfantryModifier();

    abstract public double getMechanizedInfantryModifier();

    abstract public double getMobileArtilleryModifier();

    abstract public double getTankModifier();
}
