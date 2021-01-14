package uj.pwj2020.battleships.states;

import uj.pwj2020.battleships.Game;

public class LostGame implements GameState{

    Game game;

    public LostGame(Game game) {
        this.game = game;
    }

    @Override
    public void invokeAction() {
        System.out.println("Przegrana");
        System.out.println("Enemy Map:");
        game.getEnemyMap().showMap();
        System.out.println();
        System.out.println("My Map:");
        game.getMyMap().showMap();
        game.setGameOver(true);

    }
}
