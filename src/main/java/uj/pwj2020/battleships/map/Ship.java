package uj.pwj2020.battleships.map;
import java.util.ArrayList;

public class Ship {
    private final ArrayList<Cell> cells;

    private boolean isDestroyed;


    public Ship(){
        cells =  new ArrayList<>();
        isDestroyed = false;
    }

    public boolean isDestroyed() {
        for (var cell : cells){
            if (cell.getType() != CellType.HIT){
                return false;
            }
        }
        isDestroyed = true;
        return true;
    }

    public void addCell(Cell cell){
        cells.add(cell);
    }

    public ArrayList<Cell> getCells() {
        return cells;
    }

}
