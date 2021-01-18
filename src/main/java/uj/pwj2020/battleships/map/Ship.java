package uj.pwj2020.battleships.map;
import java.util.ArrayList;

public class Ship {
    private final ArrayList<Cell> cells;

    public Ship(){
        cells =  new ArrayList<>();
    }

    public boolean isDestroyed() {
        for (var cell : cells){
            if (cell.getType() != CellType.HIT){
                return false;
            }
        }
        return true;
    }

    public void addCell(Cell cell){
        cells.add(cell);
    }

    public ArrayList<Cell> getCells() {
        return cells;
    }

}
