package uj.pwj2020.battleships.states;

import uj.pwj2020.battleships.Game;
import uj.pwj2020.battleships.map.CellType;
import uj.pwj2020.battleships.map.Map;
import uj.pwj2020.battleships.map.MapIterator;

public class EnemyTurn implements GameState{

    Game game;
    String field;

    public EnemyTurn(Game game, String field) {
        this.game = game;
        this.field = field;
    }

    @Override
    public void invokeAction() {

        Map map = game.getMyMap();

        field = field.toUpperCase();
        int row = field.charAt(0) - 'A';
        int column = Integer.parseInt(field.substring(1)) - 1;
        String message;



        if (map.getCell(row,column).getType() == CellType.WATER || map.getCell(row, column).getType() == CellType.MISS){
            map.setCellType(row,column,CellType.MISS);
            message = "pudło";
        }else{
            map.setCellType(row, column,CellType.HIT);
            if (map.getCell(row,column).getShip().isDestroyed()) {
                MapIterator iterator = map.iterator();

                if (!iterator.hasNext()){
                    message = "ostatni zatopiony";
                }else{
                    message = "trafiony zatopiony";
                }
            }else {
                message = "trafiony";
            }
        }

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
