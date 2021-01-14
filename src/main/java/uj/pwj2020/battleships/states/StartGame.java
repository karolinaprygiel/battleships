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
        send(message + ";" +field);
        game.setState(new GetResponse(game, field));

    }

    private void send(String mess) {
        try{
            System.out.println("sending message to opponent: " + mess );
            var out = game.getOut();
            out.write(mess);
            out.newLine();
            out.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
