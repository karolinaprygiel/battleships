package uj.pwj2020.battleships.players;

import uj.pwj2020.battleships.map.Map;

public class EmptyMode implements BotMode {
    @Override
    public String nextFieldToHit(Map map) {
        return "Nie wybrano strategii";
    }
}
