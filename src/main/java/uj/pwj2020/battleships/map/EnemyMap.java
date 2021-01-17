package uj.pwj2020.battleships.map;

public class EnemyMap extends Map{


    public EnemyMap() {

    }

    @Override
    public void loadMap() {
        Cell[][] emptyMap = new Cell[10][10];
        for (int i = 0; i < 10; i++){
            for(int j = 0; j <10; j++){
                emptyMap[i][j] = new Cell(i, j, '?');
            }
        }
        this.map = emptyMap;
    }

    @Override
    public MapIterator iterator() {
        return new EnemyMapIterator(this);
    }


    public void markMove(String field, String command) {

        field = field.toUpperCase();
        int row = field.charAt(0) - 'A';
        int column = Integer.parseInt(field.substring(1)) - 1;

        switch (command) {
            case "pudło" -> setCellType(row, column, CellType.WATER);
            case "trafiony" -> setCellType(row, column, CellType.SHIP);
            case "trafiony zatopiony", "ostatni zatopiony" -> {
                setCellType(row, column, CellType.SHIP);
                Ship ship = new Ship();
                buildShip(row, column, ship);
                surroundWithWater(ship);
            }
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



}
