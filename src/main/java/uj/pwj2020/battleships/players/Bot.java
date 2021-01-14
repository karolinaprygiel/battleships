package uj.pwj2020.battleships.players;
import uj.pwj2020.battleships.map.Map;

import java.util.Random;

public class Bot implements Player {

    BotMode mode = new HardMode();

    @Override
    public String hitField(Map map) {
        return mode.nextFieldToHit(map);

    }
    public void setMode(BotMode mode){
        this.mode = mode;
    }
}
