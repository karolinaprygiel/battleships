package uj.pwj2020.battleships.map;

public abstract class Map {
    Cell[][] map;


    public Map(){
    }

    public abstract void loadMap();

    public Cell[][] getMapCells(){
        return map;
    }
    public Cell getCell(int row, int column){
        return map[row][column];

    }

    public abstract MapIterator iterator();


     void buildShip(int i, int j, Ship ship) {
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

    public void setCellType(int row, int column, CellType type){
        map[row][column].setType(type);
    }


    @Override
    public String toString() {
        StringBuilder stringMap = new StringBuilder();
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                stringMap.append(map[i][j].getType().getSymbol()).append(" ");
            }
            stringMap.append(System.getProperty("line.separator"));
        }

        return stringMap.toString();

    }
}
