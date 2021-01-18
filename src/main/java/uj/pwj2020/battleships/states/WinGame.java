package uj.pwj2020.battleships.states;

import uj.pwj2020.battleships.Game;
import uj.pwj2020.battleships.map.CellType;
import uj.pwj2020.battleships.map.MapIterator;

public class WinGame implements GameState{

    Game game;

    public WinGame(Game game) {
        this.game = game;
    }
    @Override
    public void invokeAction() {

        game.getView().showMessage("Wygrana");
        game.getView().showMessage("Mapa przeciwnika:");

        MapIterator iterator = game.getEnemyMap().iterator();
        while(iterator.hasNext()){
            iterator.getNext().setType(CellType.WATER);

        }

        game.getView().showMap(game.getEnemyMap());
        game.getView().showMessage("Moja mapa:");
        game.getView().showMap(game.getMyMap());
        game.setGameOver(true);

    }
}
