package uj.pwj2020.battleships.map;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class EnemyMapIterator implements MapIterator{

    private final Map map;
    private int currIndex;

    public EnemyMapIterator(Map map) {
        this.map = map;
        this.currIndex = -1;
    }


    @Override
    public boolean hasNext() {

        int index = currIndex + 1;
        for (int i = index/10; i < 10; i++){
            for (int j = index%10; j < 10; j++){
                if (map.getMapCells()[i][j].getType() ==  CellType.UNKNOWN){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Cell getNext() {
        int index = currIndex + 1;

        for (int i = index/10; i < 10; i++){
            for (int j = index%10; j < 10; j++){
                if (map.getMapCells()[i][j].getType() == CellType.UNKNOWN){
                    currIndex = i*10 + j;
                    return map.getMapCells()[i][j];
                }
            }
        }
        return null;
    }

    public Cell getRandom(){

        ArrayList<Cell> cells = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (map.getMapCells()[i][j].getType() == CellType.UNKNOWN) {
                    cells.add(map.getMapCells()[i][j]);
                    }
                }

            }

        if (cells.size() == 0){
            return null;
        }

        Random random = new Random();
        return cells.get(random.nextInt( cells.size()));

            }




}
