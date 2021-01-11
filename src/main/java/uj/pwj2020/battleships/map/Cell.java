package uj.pwj2020.battleships.map;

public class Cell {
    private final int row;
    private final int column;
    private CellType type;
    private Ship ship = null;

    public Cell(int row, int column, char symbol){
        this.row = row;
        this.column = column;
        this.type = CellType.valueOf(symbol);

    }
    public Cell(int row, int column, CellType type){
        this.row = row;
        this.column = column;
        this.type = type;

    }

    public void setType(CellType type) {
        this.type = type;
    }

    public CellType getType() {
        return type;
    }

    public Ship getShip(){
        return ship;
    }
    public void setShip(Ship ship){
        this.ship = ship;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
