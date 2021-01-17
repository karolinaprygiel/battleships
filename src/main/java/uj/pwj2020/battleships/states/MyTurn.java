package uj.pwj2020.battleships.states;

import uj.pwj2020.battleships.Game;

public class MyTurn implements GameState{
    Game game;
    String message;

    public MyTurn(Game game, String message) {
        this.game = game;
        this.message = message;
    }

    @Override
    public void invokeAction() {
        game.getView().showMessage("Enemy Map:");
        game.getView().showMap(game.getEnemyMap());
        game.getView().showMessage("My Map:");
        game.getView().showMap(game.getMyMap());
        game.getView().showMessage("Make move:");
        String field = game.getPlayer().hitField(game.getEnemyMap());
        game.send(message + ";" + field);
        game.setState(new GetResponse (game, field));

    }


}
