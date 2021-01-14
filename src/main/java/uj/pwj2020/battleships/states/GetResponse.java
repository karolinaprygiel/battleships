package uj.pwj2020.battleships.states;

import uj.pwj2020.battleships.Game;

import java.io.IOException;
import java.util.Arrays;

public class GetResponse implements GameState{

    Game game;
    String lastMove;

    public GetResponse(Game game, String lastMove) {
        this.game = game;
        this.lastMove = lastMove;
    }

    public GetResponse(Game game) {
        this.game = game;
        this.lastMove = "";
    }


    @Override
    public void invokeAction() {
        String enemyMessage = "";
        try {
            enemyMessage = game.getIn().readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(enemyMessage);

        String command = getCommand(enemyMessage);
        String field = getField(enemyMessage);

        if (checkValid(enemyMessage)) {
            if (!command.equals("start")) {
                game.getEnemyMap().markMove(lastMove, command);
            }

            if (command.equals("ostatni zatopiony")) {
                game.setState(new WinGame(game));
            } else if (command.equals("błąd komunikacji")){
                game.setState(new EndGame(game));
            }else {
                game.setState(new EnemyTurn(game, field));
            }

        } else {
                //System.out.println("błąd komunikacji");
                send("błąd komunikacji");
                game.setState(new EndGame(game));

        }

    }

    private String getField(String message) {
        if (message.contains(";")) {
            return message.substring(message.indexOf(';') + 1);
        }
        return "";
    }

    private String getCommand(String message) {
        if (message.equals("ostatni zatopiony") || message.equals("błąd komunikacji")) {
            return message;
        }
        return message.substring(0,message.indexOf(';'));
    }

    private boolean checkValid(String message) {
        String command = getCommand(message);
        String field = getField(message);

        if (!Arrays.asList("start", "pudło", "ostatni zatopiony", "trafiony", "trafiony zatopiony", "błąd komunikacji").contains(command)) {
            return false;
        }
        if (command.equals("ostatni zatopiony") || command.equals("błąd komunikacji")) {
            return true;
        }

        int row = field.charAt(0) - 'A';
        int column = Integer.parseInt(field.substring(1)) - 1;
        return row >= 0 && row < 10 && column >= 0 && column < 10;

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
