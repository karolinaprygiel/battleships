package uj.pwj2020.battleships.players;

import uj.pwj2020.battleships.map.Map;

public class EmptyPlayer implements Player {
    @Override
    public String hitField(Map map) {
        return null;
    }
}
