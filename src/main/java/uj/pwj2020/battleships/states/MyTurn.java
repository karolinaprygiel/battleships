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

        game.getView().showMessage("Mapa przeciwnika:");
        game.getView().showMap(game.getEnemyMap());
        game.getView().showMessage("Moja mapa:");
        game.getView().showMap(game.getMyMap());
        game.getView().showMessage("wykonaj ruch:");
        String field = game.getPlayer().hitField(game.getEnemyMap());
        game.send(message + ";" + field);
        game.setState(new GetResponse (game, field));

    }


}
