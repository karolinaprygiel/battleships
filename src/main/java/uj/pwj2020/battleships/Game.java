package uj.pwj2020.battleships;

import uj.pwj2020.battleships.map.Map;
import uj.pwj2020.battleships.players.Computer;
import uj.pwj2020.battleships.players.Human;
import uj.pwj2020.battleships.players.Player;
import java.io.*;
import java.net.Socket;
import java.nio.file.Path;
import java.util.Arrays;


public class Game {
    private BufferedWriter out;
    private BufferedReader in;
    private Player player;
    private Map myMap;
    private Map enemyMap;
    private GameState state;
    private String message = "";
    private String lastMove ="";
    private String enemyLastMessage = "";
    private int invalidCounter = 0;


    private Game(){

    }
    public static GameBuilder builder() {
        return new GameBuilder();
    }

    public void playGame() {

        System.out.println("My map:");
        myMap.showMap();

        try {
            while (true) {
                if (state == GameState.STARTGAME){
                    startGame();
                }else if(state == GameState.MYTURN){
                    myTurn();
                }else if (state == GameState.GETRESPONSE){
                    getEnemyResponse();
                } else if (state == GameState.ENEMYTURN){
                    processEnemyTurn();
                }else if (state == GameState.END){
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void startGame() {
        String field = player.hitField();
        message = "start";
        send(message + ";" +field);
        lastMove = field;
        state = GameState.GETRESPONSE;
    }

    private void myTurn() {
//        System.out.println("My map:");
//        myMap.showMap();
//        System.out.println("Enemy map:");
//        enemyMap.showMap();
//        System.out.println("Make move");
        String field = player.hitField();
        lastMove = field;
        send(message + ";" + field);
        state = GameState.GETRESPONSE;
    }

    private void getEnemyResponse() throws IOException {
        String enemyMessage = in.readLine();
        System.out.println(enemyMessage);


        if (enemyLastMessage.equals(enemyMessage)) { //enemy resend his meessage if my message was invalid
            state = GameState.MYTURN;
            return;
        }
        enemyLastMessage = enemyMessage;
        String command = getCommand(enemyMessage);

        if (checkValid(enemyMessage)) {
            invalidCounter = 0;
            if (!command.equals("start")) {
                enemyMap.markMove(lastMove, command);
            }

            if (command.equals("ostatni zatopiony")) {
                state = GameState.END;
                win();
            } else if (command.equals("błąd komunikacji")){
                state = GameState.END;
            }else {
                state = GameState.ENEMYTURN;
            }

        } else {
            if (invalidCounter == 2){
                System.out.println("błąd komunikacji");
                send("błąd komunikacji");
                state = GameState.END;
            }else {
                send(message + ";" + lastMove);
                invalidCounter++;
            }
        }
    }

    private void processEnemyTurn() {
        String enemyMessage = enemyLastMessage;
        String field = getField(enemyMessage);

        message = myMap.processHit(field);
        if (message.equals("ostatni zatopiony")){
            send(message);
            state = GameState.END;
            lose();
        }else {
            state = GameState.MYTURN;
        }
    }

    private boolean checkValid(String message) {
        String command = getCommand(message);
        String field = getField(message);

        if (!Arrays.asList("start", "pudło", "ostatni zatopiony", "trafiony", "trafiony zatopiony", "błąd komunikacji").contains(command)){
            return false;
        }
        if(command.equals("ostatni zatopiony") || command.equals("błąd komunikacji")){
            return true;
        }

        int row = field.charAt(0) - 'A';
        int column = Integer.parseInt(field.substring(1)) - 1;
        return row >= 0 && row < 10 && column >= 0 && column < 10;
    }


    private void win() {
        System.out.println("Wygrana");
        System.out.println("Enemy Map:");
        enemyMap.showFullMap();
        System.out.println();
        System.out.println("My Map:");
        myMap.showMap();
    }

    private void lose() {
        System.out.println("Przegrana");
        System.out.println("Enemy Map:");
        enemyMap.showMap();
        System.out.println();
        System.out.println("My Map:");
        myMap.showMap();
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

    private void send(String mess) {
        try{
            System.out.println("sending message to opponent: " + mess );
            out.write(mess);
            out.newLine();
            out.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static final class GameBuilder{
        private BufferedWriter out;
        private BufferedReader in;
        private Player player;
        private Map myMap;
        private Map enemyMap;
        private GameState state;
        private String message;
        private String lastMove;
        private String enemyLastMessage;
        private int invalidCounter;

        public GameBuilder buildOut(Socket socket){
            try {
                this.out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return this;
        }
        public GameBuilder buildIn(Socket socket){
            try {
                this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return this;
        }
        public GameBuilder buildPlayer(String s){
            this.player = new Computer();
            return this;
        }
        public GameBuilder buildMyMap(Path path){
            this.myMap = Map.loadMapFromFile(path);
            return this;
        }
        public GameBuilder buildEnemyMap(){
            this.enemyMap = Map.getMapOfUnnknowns();
            return this;
        }
        public GameBuilder buildState(GameState state){
            this.state = state;
        return this;
        }
        public GameBuilder buildMessage(){
            this.message = "";
            return this; }
        public GameBuilder buildLastMove(){
            this.lastMove = "";
            return this; }
        public GameBuilder buildEnemyLastMessage(){
            this.enemyLastMessage = "";
            return this; }
        public GameBuilder buildInvalidCounter(){
            this.invalidCounter = 0;
            return this; }

        public Game buid(){
            Game game = new Game();
            game.out = this.out;
            game.in = this.in;
            game.player = this.player;
            game.myMap = this.myMap;
            game.enemyMap = this.enemyMap;
            game.state = this.state;
            game.invalidCounter = this.invalidCounter;
            game.message = this.message;
            game.lastMove = this.lastMove;
            game.enemyLastMessage = this.enemyLastMessage;

            return game;
        }


    }


}
