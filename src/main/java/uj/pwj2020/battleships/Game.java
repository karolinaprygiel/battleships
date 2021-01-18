package uj.pwj2020.battleships;

import uj.pwj2020.battleships.inputReceiver.InputReceiver;
import uj.pwj2020.battleships.map.EnemyMap;
import uj.pwj2020.battleships.map.PlayerMap;
import uj.pwj2020.battleships.players.Player;
import uj.pwj2020.battleships.players.PlayerFactory;
import uj.pwj2020.battleships.states.EmptyState;
import uj.pwj2020.battleships.states.GameState;
import uj.pwj2020.battleships.view.GameView;

import java.io.*;
import java.net.Socket;



public class Game {
    private BufferedWriter out;
    private BufferedReader in;
    private Player player;
    private PlayerMap myMap;
    private EnemyMap enemyMap;
    private GameState state;
    private GameView view;
    private InputReceiver receiver;
    boolean isGameOver;


    private Game(){

    }
    public static GameBuilder builder() {
        return new GameBuilder();
    }

    public void playGame() {
        view.showMessage("Moja mapa");
        view.showMap(myMap);

        while (!isGameOver) {
            state.invokeAction();

        }
    }

    public void send(String mess) {
        try{
            view.showMessage("wysyłanie wiadomości do użytkownika: " + mess );
            view.showMessage("");
            out.write(mess);
            out.newLine();
            out.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setState(GameState state){
        this.state = state;
    }


    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }


    public Player getPlayer() {
        return player;
    }

    public PlayerMap getMyMap() {
        return myMap;
    }

    public EnemyMap getEnemyMap() {
        return enemyMap;
    }

    public GameView getView() {
        return view;
    }

    public InputReceiver getReceiver(){
        return receiver;
    }

    public BufferedReader getIn() {
        return in;
    }

    public static final class GameBuilder{
        private BufferedWriter out;
        private BufferedReader in;
        private Player player;
        private PlayerMap myMap;
        private EnemyMap enemyMap;
        private GameState state;
        private GameView view;
        private InputReceiver receiver;
        private boolean isGameOver;

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
        public GameBuilder buildPlayer(String playerType, String mode){
            this.player = PlayerFactory.getPlyer(playerType, mode);
            return this;
        }


        public GameBuilder buildMyMap(){
            this.myMap = PlayerMap.getInstance();
            myMap.loadMap();
            return this;
        }
        public GameBuilder buildEnemyMap(){
            this.enemyMap = EnemyMap.getInstance();
            enemyMap.loadMap();
            return this;
        }
        public GameBuilder buildState(){
            this.state = new EmptyState();
          return this;
        }

        public GameBuilder buildState(GameState state){
            this.state = state;
            return this;
        }

        public GameBuilder buildView(GameView view){
            this.view = view;
            return this;
        }

        public GameBuilder builReceiver(InputReceiver receiver) {
            this.receiver = receiver;
            return this;
        }

        public GameBuilder gameNotOver() {
            this.isGameOver = false;
            return this;
        }

        public GameBuilder gameOver() {
            this.isGameOver = true;
            return this;
        }



        public Game buid(){
            Game game = new Game();
            game.out = this.out;
            game.in = this.in;
            game.player = this.player;
            game.myMap = this.myMap;
            game.enemyMap = this.enemyMap;
            game.state = this.state;
            game.view = this.view;
            game.receiver = this.receiver;
            game.isGameOver = this.isGameOver;

            return game;
        }



    }


}
