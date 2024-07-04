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
    protected int minAttackRange;
    protected int maxAttackRange;
    protected boolean attackAir = false;
    protected boolean attackGround = false;
    protected int movementRange;
    protected int movementCosts [] = new int [4];
    protected ImageView imageView;
    protected int row;
    protected int col;
    protected boolean blocked;
    protected String team;


    public unit(String name, int health, int attackPower, int movementRange, String imagePath, String team, boolean blocked, int movementWood, int movementPlain, int movementSea, int movementMountain, int minAttackRange, int maxAttackRange,int atackPossibilitys) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
        this.movementRange = movementRange;
        this.imageView = new ImageView(new Image(getClass().getResource(imagePath).toExternalForm()));
        this.team = team;
        this.blocked = blocked;
        this.movementCosts[0] = movementWood;
        this.movementCosts[1] = movementPlain;
        this.movementCosts[2] = movementSea;
        this.movementCosts[3] = movementMountain;
        this.minAttackRange = minAttackRange;
        this.maxAttackRange = maxAttackRange;
        attackAir = checkAir(atackPossibilitys);
        attackAir = checkGround(atackPossibilitys);
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

    public int getMinAttackRange(){
     return minAttackRange;
    }
    public int getMaxAttackRange(){
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
    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void move(int newRow, int newCol) {
        setPosition(newRow, newCol);
        // Additional logic to update the UI can be added here
    }

    public boolean canMoveTo(int newRow, int newCol,Terrain terrain) {
        int distance = Math.abs(newRow - row) + Math.abs(newCol - col);
        int movementCosts = getMovementCost(terrain);
        int newDistance = distance + movementCosts - 1;
        return newDistance <= movementRange;
    }




    // Method to calculate movement cost based on terrain
    public int getMovementCost(Terrain terrain) {
        int costs;
        switch (terrain.getName()) {
            case "water":
                costs = movementCosts[2];
                break;
            case "mountain":
                costs = movementCosts[3];
                break;
            case "wood":
                costs = movementCosts[0];
                break;

            default:
                costs = movementCosts[1];
                break;
        }
        return costs;
    }

    public boolean checkAir(int attackPossibilitys) {
    if(attackPossibilitys==1 || attackPossibilitys == 3){
        return true;
    }
        return false;
    }

    public boolean checkGround(int attackPossibilitys) {
        if(attackPossibilitys==2 || attackPossibilitys == 3){
            return true;
        }
        return false;
    }
}

