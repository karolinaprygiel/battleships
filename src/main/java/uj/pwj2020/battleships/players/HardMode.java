package uj.pwj2020.battleships.players;

import uj.pwj2020.battleships.map.Cell;
import uj.pwj2020.battleships.map.Map;
import uj.pwj2020.battleships.map.MapIterator;

public class HardMode implements BotMode{


    @Override
    public String nextFieldToHit(Map map) {

        MapIterator iterator= map.iterator();
        Cell cell = iterator.getRandom();
        return Cell.cordsToFieldName(cell.getRow(), cell.getColumn());

    }
}
