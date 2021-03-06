package uj.pwj2020.battleships.states;

import uj.pwj2020.battleships.Game;

public class LostGame implements GameState{

    Game game;

    public LostGame(Game game) {
        this.game = game;
    }

    @Override
    public void invokeAction() {
        game.getView().showMessage("Przegrana");
        game.getView().showMessage("Mapa przeciwnika:");
        game.getView().showMap(game.getEnemyMap());
        game.getView().showMessage("Moja mapa:");
        game.getView().showMap(game.getMyMap());
        game.setGameOver(true);

    }
}
