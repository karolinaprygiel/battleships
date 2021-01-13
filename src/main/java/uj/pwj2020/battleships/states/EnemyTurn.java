package uj.pwj2020.battleships.states;

import uj.pwj2020.battleships.Game;

public class EnemyTurn implements GameState{

    Game game;
    String field;

    public EnemyTurn(Game game, String field) {
        this.game = game;
        this.field = field;
    }

    @Override
    public void invokeAction() {


        String message = game.getMyMap().processHit(field);
        if (message.equals("ostatni zatopiony")){
            send(message);
            game.setState(new LostGame(game));
        }else {
            game.setState(new MyTurn(game, message));
        }

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
