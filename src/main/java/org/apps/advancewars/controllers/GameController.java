package org.apps.advancewars.controllers;

import org.apps.advancewars.units.Bomber;
import org.apps.advancewars.units.Fighter;
import org.apps.advancewars.units.unit;

import java.util.ArrayList;
import java.util.List;


public class GameController {
    String player1;
    String player2;
    List<unit> player1Units = new ArrayList<unit>();
    List<unit> player2Units = new ArrayList<unit>();
public GameController(String player1name, String player2name) {
    this.player1 = player1name;
    this.player2 = player2name;
    player1Units.add(new Fighter(player1name));
    player1Units.add(new Bomber(player1name));
    player2Units.add(new Fighter(player2name));
    player2Units.add(new Bomber(player2name));

}
    public void checkPlayed() {
        System.out.println("Checking units for player: " + player1);
        checkPlayerUnits(player1Units);

        System.out.println("Checking units for player: " + player2);
        checkPlayerUnits(player2Units);
    }

    private void checkPlayerUnits(List<unit> units) {
        for (unit unit : units) {
            if (unit.getMovedThisTurn() || unit.getAttackThisTurn()) {
                System.out.println("Unit " + unit.getName() + " has played (moved: " + unit.getMovedThisTurn() + ", attacked: " + unit.getAttackThisTurn() + ").");
            } else {
                System.out.println("Unit " + unit.getName() + " has not played.");
            }
        }
    }
}