package uj.pwj2020.battleships.map;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Map {
    private Cell[][] map;
    private ArrayList<Ship> ships;

    public Map(Cell[][] map){

        this.map = map;
        this.ships = new ArrayList<>();
        loadShips();
    }

    public static Map loadMapFromFile(Path path){
        Cell[][] loadedMap = new Cell[10][10];

        try {
            var lines = Files.readAllLines(path);
            for (int i = 0; i <10; i++){
                for (int j = 0; j < 10; j++){
                    char symbol = lines.get(i).charAt(j);
                    loadedMap[i][j] = new Cell(i, j, symbol);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Map(loadedMap);
    }

    public static Map getMapOfUnnknowns(){
        Cell[][] emptyMap = new Cell[10][10];
        for (int i = 0; i < 10; i++){
            for(int j = 0; j <10; j++){
                emptyMap[i][j] = new Cell(i, j, '?');
            }
        }
        return new Map(emptyMap);
    }

    public void loadShips(){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (map[i][j].getType() == CellType.SHIP && map[i][j].getShip() == null){
                    Ship ship = new Ship();
                    ships.add(ship);
                    buildShip(i,j,ship);

                }
            }
        }
    }

    private void buildShip(int i, int j, Ship ship) {
        if (i >= 0 && i < 10 && j >= 0 && j < 10 &&
                map[i][j].getType() ==  CellType.SHIP && map[i][j].getShip() == null){

            ship.addCell(map[i][j]);
            map[i][j].setShip(ship);

            buildShip(i, j-1, ship);
            buildShip(i-1, j,ship);
            buildShip(i, j+1, ship);
            buildShip(i+1,j, ship);
        }

    }


    public void markMove(String field, String command) {

        field = field.toUpperCase();
        int row = field.charAt(0) - 'A';
        int column = Integer.parseInt(field.substring(1)) - 1;

        if (command.equals("pudło")) {
            setCellType(row,column, CellType.WATER);

        } else if (command.equals("trafiony")){
            setCellType(row,column,CellType.SHIP);


        } else if(command.equals("trafiony zatopiony") || command.equals("ostatni zatopiony")) {
            setCellType(row,column,CellType.SHIP);

            Ship ship = new Ship();
            buildShip(row,column,ship);
            surroundWithWater(ship);

        }

    }

    private void surroundWithWater(Ship ship) {
        for (var cell : ship.getCells()){
            int row = cell.getRow();
            int column = cell.getColumn();

            if (row - 1 >= 0 && map[row-1][column].getType() == CellType.UNKNOWN){
                map[row-1][column].setType(CellType.WATER);
            }
            if (column - 1 >= 0 && map[row][column-1].getType() == CellType.UNKNOWN){
                map[row][column-1].setType(CellType.WATER);
            }
            if (row + 1 < 10  && map[row+1][column].getType() == CellType.UNKNOWN){
                map[row+1][column].setType(CellType.WATER);
            }
            if (column + 1 < 10 && map[row][column+1].getType() == CellType.UNKNOWN){
                map[row][column + 1].setType(CellType.WATER);
            }

            if (row - 1 >= 0 && column - 1 >= 0 && map[row-1][column-1].getType() == CellType.UNKNOWN){
                map[row-1][column-1].setType(CellType.WATER);
            }
            if (row - 1 >= 0 && column + 1 < 10 && map[row-1][column+1].getType() == CellType.UNKNOWN){
                map[row-1][column+1].setType(CellType.WATER);
            }
            if (row + 1 < 10  && column - 1 >= 0 && map[row+1][column-1].getType() == CellType.UNKNOWN){
                map[row+1][column-1].setType(CellType.WATER);
            }
            if (row+1 < 10 && column + 1 < 10 && map[row+1][column+1].getType() == CellType.UNKNOWN){
                map[row+1][column + 1].setType(CellType.WATER);
            }
        }
    }

    private void setCellType(int row, int column, CellType type){
        map[row][column].setType(type);
    }


    public String processHit(String field) {
        field = field.toUpperCase();
        int row = field.charAt(0) - 'A';
        int column = Integer.parseInt(field.substring(1)) - 1;

        if (map[row][column].getType() == CellType.WATER || map[row][column].getType() == CellType.MISS){
            setCellType(row,column,CellType.MISS);
            return  "pudło";
        }else{
            map[row][column].setType(CellType.HIT);
            if (map[row][column].getShip().isDestroyed()) {
                if (allShipsDestroyed()){
                    return "ostatni zatopiony";
                }
                return "trafiony zatopiony";
            }
            return "trafiony";
        }
    }

    private boolean allShipsDestroyed() {
        for (var ship : ships){
            if (!ship.isDestroyed()){
                return false;
            }
        }
        return true;
    }

    public void showFullMap() {
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                if (map[i][j].getType() == CellType.UNKNOWN){
                    System.out.print(CellType.WATER.getSymbol() + " ");
                }else{
                System.out.print(map[i][j].getType().getSymbol() + " ");
            }
            }
            System.out.println();
        }
    }

    public void showMap() {
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                System.out.print(map[i][j].getType().getSymbol() + " ");
            }
            System.out.println();
        }
    }


}
