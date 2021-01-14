package uj.pwj2020.battleships.players;

import uj.pwj2020.battleships.map.Cell;
import uj.pwj2020.battleships.map.Map;

import java.util.Random;

public class EasyMode  implements BotMode{
    @Override
    public String nextFieldToHit(Map map) {
        Random random = new Random();

        return Cell.cordsToFieldName(random.nextInt(10), random.nextInt(10));
    }
}
