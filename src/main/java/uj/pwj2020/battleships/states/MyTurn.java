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

        //        System.out.println("My map:");
//        myMap.showMap();
//        System.out.println("Enemy map:");
//        enemyMap.showMap();
//        System.out.println("Make move");
        String field = game.getPlayer().hitField();
        send(message + ";" + field);
        game.setState(new GetResponse (game, field));

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
