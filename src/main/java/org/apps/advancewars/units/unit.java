package org.apps.advancewars.units;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.apps.advancewars.terrain.Terrain;

public abstract class unit {
    protected String name;
    protected int health;
    protected int attackPower;
    protected int minAttackRange;
    protected int maxAttackRange;
    protected boolean attackAir = false;
    protected boolean attackMiddle = false;
    protected boolean attackGround = false;
    protected int movementRange;
    protected int movementCosts[] = new int[4];
    protected ImageView imageView;
    protected int row;
    protected int col;
    protected int troopCategory;
    protected boolean movementBlocked = false;
    protected boolean attackBlocked = false;
    protected String team;

    public unit(String name, int health, int attackPower, int movementRange, String imagePath, String team, int movementWood, int movementPlain, int movementSea, int movementMountain, int minAttackRange, int maxAttackRange, boolean canAttackGround, boolean canAttackMiddle, boolean canAttackAir, int troopCategory) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
        this.movementRange = movementRange;
        this.team = team;
        this.movementCosts[0] = movementWood;
        this.movementCosts[1] = movementPlain;
        this.movementCosts[2] = movementSea;
        this.movementCosts[3] = movementMountain;
        this.minAttackRange = minAttackRange;
        this.maxAttackRange = maxAttackRange;
        this.attackGround = canAttackGround;
        this.attackMiddle = canAttackMiddle;
        this.attackAir = canAttackAir;
        this.troopCategory = troopCategory;
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

    public int getTroopCategory() {
        return troopCategory;
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

    public boolean[] getAttackPossibilities() {
        return new boolean[]{attackGround, attackMiddle, attackAir};
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
        // Additional logic to update the UI can be added here
    }

    public boolean canMoveTo(int newRow, int newCol, Terrain terrain) {
        int distance = Math.abs(newRow - row) + Math.abs(newCol - col);
        int movementCosts = getMovementCost(terrain);
        int newDistance = distance + movementCosts - 1;
        return newDistance <= movementRange;
    }

    public boolean canAttack(unit enemy,int newRow, int newCol) {
        int distance = Math.abs(newRow - row) + Math.abs(newCol - col);
        return distance >= getMinAttackRange() && distance <= getMaxAttackRange()&&!getAttackBlocked();
    }

    // Method to calculate movement cost based on terrain
    public int getMovementCost(Terrain terrain) {
        switch (terrain.getName()) {
            case "water":
                return movementCosts[2];
            case "mountain":
                return movementCosts[3];
            case "wood":
                return movementCosts[0];
            default:
                return movementCosts[1];
        }
    }

    public void reset() {
        this.movementBlocked = false;
        this.attackBlocked = false;
    }
}
