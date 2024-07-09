package org.apps.advancewars.controllers;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import org.apps.advancewars.units.unit;
import org.apps.advancewars.terrain.Terrain;

import java.util.HashMap;
import java.util.Map;

public class UnitController {
    private final int TILE_SIZE;
    private GridPane gameGridPane;
    private Map<String, unit> units;
    private unit selectedUnit;
    private unit selectedField;
    private MapController mapController;
    private String player = "teamred";

    public UnitController(GridPane gameGridPane, int tileSize, MapController mapController) {
        this.gameGridPane = gameGridPane;
        this.TILE_SIZE = tileSize;
        this.units = new HashMap<>();
        this.mapController = mapController;
    }

    public void placeUnit(unit unit, int row, int col) {
        units.put(unit.getName() + row + "_" + col, unit); // Unique key for each unit
        unit.setPosition(row, col);
        ImageView imageView = unit.getImageView();
        imageView.setFitWidth(TILE_SIZE);
        imageView.setFitHeight(TILE_SIZE);
        gameGridPane.add(imageView, col, row);

    }

    public void handleMouseClick(MouseEvent event) {
        int col = (int) (event.getX() / TILE_SIZE);
        int row = (int) (event.getY() / TILE_SIZE);

     //   if (selectedUnit == null) {
            // Select a unit if one is at the clicked position
       //     selectedUnit = getUnitAt(row, col);
      //  } else {
       //     unit enemyUnit = getUnitAt(row, col);
       //     if(enemyUnit==null&&canMoveTo(selectedUnit, row, col)&& !selectedUnit.getMovementBlocked()){
        //        moveUnit(selectedUnit, row, col);
          //  }
         //   if(enemyUnit!=null) {

          //      if (enemyUnit.getTeam() != player && selectedUnit.canAttack(enemyUnit, row, col) && !selectedUnit.getAttackBlocked()) {
             //       attack(enemyUnit, selectedUnit);
             //   } else if (enemyUnit.getTeam() == player) {
              //      selectedUnit = enemyUnit;
                    //Hier werden auf der Benutzeroberfläche aktuelle Truppeninfos ausgegeben
                    //z.B Label.setText("Die Truppe hat noch " + selectedUnit.getHealth + "Leben"
            //    }
           // }
            // Versuchen die ausgewählte truppe zu bewegen
         //   if (selectedUnit.getTeam().equals(player)) {

           //     if(areAllUnitsBlocked()){
            //        changePlayer();
            //    }
          //  }
          //  selectedUnit = null; // Truppe wieder abwählen
       // }
        //}
if(selectedUnit==null&&getUnitAt(row, col)!=null){
if(getUnitAt(row, col).getTeam()==player){
    selectedUnit = getUnitAt(row,col);
}
//Anzeigen von Daten
}
else if(selectedUnit!=null){
    selectedField = getUnitAt(row,col);
    //Anzeigen von Daten
    if(selectedField == null&&canMoveTo(selectedUnit, row, col)&& !selectedUnit.getMovementBlocked()){
        //bewegen

        moveUnit(selectedUnit,row,col);
        selectedUnit=null;
        selectedField=null;

    }
    else if(selectedField!=null&&selectedField.getTeam()!=player&& selectedUnit.canAttack(selectedField, row, col) && !selectedUnit.getAttackBlocked()){
        //angreifen

        attack(selectedField,selectedUnit);
        selectedUnit=null;
        selectedField=null;
    }
    else if(selectedField!=null && selectedField.getTeam()==player){
        selectedUnit = selectedField;
        selectedField = null;
    }
    if(areAllUnitsBlocked()&&!canAnyAttack()){
               changePlayer();
            }
}
    }


    private unit getUnitAt(int row, int col) {
        return units.values().stream()
                .filter(unit -> unit.getRow() == row && unit.getCol() == col)
                .findFirst()
                .orElse(null);
    }

    private boolean canMoveTo(unit unit, int row, int col) {
        Terrain terrain = mapController.getTerrainAt(row, col);
        // Check if there is no other unit at the destination
        if (getUnitAt(row, col) != null) {
            return false;
        }

        // Check if the unit can move to the destination within its movement range
        if (!unit.canMoveTo(row, col,terrain)) {
            return false;
        }
        return unit.canMoveTo(row, col, terrain);

    }


    private void moveUnit(unit unit, int newRow, int newCol) {
        // Remove the unit from the old position in the map
        units.remove(unit.getName() + unit.getRow() + "_" + unit.getCol());

        // Update the UI
        gameGridPane.getChildren().remove(unit.getImageView());
        unit.move(newRow, newCol);
        gameGridPane.add(unit.getImageView(), newCol, newRow);

        // Add the unit to the new position in the map
        units.put(unit.getName() + newRow + "_" + newCol, unit);
        unit.setMovementBlocked(true);
    }
    public boolean areAllUnitsBlocked() {
        for (Map.Entry<String, unit> entry : units.entrySet()) {
            unit unit = entry.getValue();
            if (unit.getTeam().equals(player) && !unit.getMovementBlocked()) {
                return false;
            }
        }
        return true;
    }

    public void unblockUnits(){
        for (Map.Entry<String, unit> entry : units.entrySet()) {
            unit unit = entry.getValue();
            if(unit.getTeam().equals(player)){
                unit.reset();
            }

        }
    }
    public void changePlayer() {
        if (player.equals("teamred")) {
            player = "teamblue";

        }
        else if (player.equals("teamblue")) {
            player = "teamred";
        }
        unblockUnits();
    }
    public void attack(unit defensive,unit offensive){
        defensive.setHealth(defensive.getHealth()-offensive.getAttackPower());
        offensive.setAttackBlocked(true);
    }
    public boolean canAnyAttack() {
        // Durchsuche alle Einheiten auf der Karte
        for (Map.Entry<String, unit> entry : units.entrySet()) {
            unit ownUnit = entry.getValue();


            if (ownUnit.getTeam().equals(player) && !ownUnit.getAttackBlocked()) {
                int row = ownUnit.getRow();
                int col = ownUnit.getCol();
                int minAttackRange = ownUnit.getMinAttackRange();
                int maxAttackRange = ownUnit.getMaxAttackRange();

                // Prüfe in alle Richtungen innerhalb der Angriffsreichweite
                for (int i = -maxAttackRange; i <= maxAttackRange; i++) {
                    for (int j = -maxAttackRange; j <= maxAttackRange; j++) {
                        if (Math.abs(i) + Math.abs(j) < minAttackRange || Math.abs(i) + Math.abs(j) > maxAttackRange) continue; // Angriffsreichweite prüfen

                        int targetRow = row + i;
                        int targetCol = col + j;

                        // Überprüfen, ob die Zielposition innerhalb der Grenzen der Karte liegt
                        if (mapController.isWithinBounds(targetRow, targetCol)) {
                            unit targetUnit = getUnitAt(targetRow, targetCol);

                            // Prüfe, ob die Zielposition eine gegnerische Einheit enthält
                            if (targetUnit != null && !targetUnit.getTeam().equals(player) && ownUnit.canAttack(targetUnit, targetRow, targetCol)) {
                                return true; // Eine Einheit kann angreifen
                            }
                        }
                    }
                }
            }
        }
        return false; // Keine Einheit kann angreifen
    }

}