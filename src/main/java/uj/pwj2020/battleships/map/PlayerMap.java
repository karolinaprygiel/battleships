package uj.pwj2020.battleships.map;

import uj.pwj2020.battleships.generator.BattleshipGenerator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class PlayerMap extends Map {


    public PlayerMap() {

    }

    @Override
    public void loadMap() {
        BattleshipGenerator gen = BattleshipGenerator.getInstance();
        String[] generatedMap = gen.generateMap();
        Cell[][] loadedMap = new Cell[10][10];
        for (int i = 0; i <10; i++){
            for (int j = 0; j < 10; j++){
                char symbol = generatedMap[i].charAt(j);
                loadedMap[i][j] = new Cell(i, j, symbol);
            }
        }
        this.map = loadedMap;
        loadShips();

    }

    @Override
    public MapIterator iterator() {
        return new PlayerMapIterator(this);
    }


    public void loadShips(){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (map[i][j].getType() == CellType.SHIP && map[i][j].getShip() == null){
                    Ship ship = new Ship();
                    buildShip(i,j,ship);

                }
            }
        }
    }




}