package uj.pwj2020.battleships.states;

import uj.pwj2020.battleships.Game;

public class EndGame implements GameState{


    Game game;

    public EndGame(Game game) {
        this.game = game;
    }


    @Override
    public void invokeAction() {
        game.setGameOver(true);

    }


}

