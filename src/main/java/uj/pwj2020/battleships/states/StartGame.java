package uj.pwj2020.battleships.states;

import uj.pwj2020.battleships.Game;

public class StartGame implements GameState {

    Game game;

    public StartGame(Game game) {
        this.game = game;
    }


    @Override
    public void invokeAction() {
        String field = game.getPlayer().hitField(game.getEnemyMap());
        String message = "start";
        game.send(message + ";" +field);
        game.setState(new GetResponse(game, field));

    }



}
