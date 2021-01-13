package uj.pwj2020.battleships.states;

import uj.pwj2020.battleships.Game;

public class WinGame implements GameState{

    Game game;

    public WinGame(Game game) {
        this.game = game;
    }
    @Override
    public void invokeAction() {

        System.out.println("Wygrana");
        System.out.println("Enemy Map:");
        game.getEnemyMap().showFullMap();
        System.out.println();
        System.out.println("My Map:");
        game.getMyMap().showMap();
        game.setGameOver(true);

    }
}
