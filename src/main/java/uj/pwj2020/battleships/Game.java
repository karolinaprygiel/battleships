package uj.pwj2020.battleships;

import uj.pwj2020.battleships.map.EnemyMap;
import uj.pwj2020.battleships.map.Map;
import uj.pwj2020.battleships.map.PlayerMap;
import uj.pwj2020.battleships.players.Player;
import uj.pwj2020.battleships.players.PlayerFactory;
import uj.pwj2020.battleships.states.EmptyState;
import uj.pwj2020.battleships.states.GameState;

import java.io.*;
import java.net.Socket;



public class Game {
    private BufferedWriter out;
    private BufferedReader in;
    private Player player;
    private PlayerMap myMap;
    private EnemyMap enemyMap;
    private GameState state;
    boolean isGameOver = false;


    private Game(){

    }
    public static GameBuilder builder() {
        return new GameBuilder();
    }

    public void playGame() {

        System.out.println("My map:");
        myMap.showMap();
        while (!isGameOver) {
            state.invokeAction();

        }
    }

    public void setState(GameState state){
        this.state = state;
    }


    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }



    public BufferedWriter getOut() {
        return out;
    }

    public BufferedReader getIn() {
        return in;
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


    public static final class GameBuilder{
        private BufferedWriter out;
        private BufferedReader in;
        private Player player;
        private PlayerMap myMap;
        private EnemyMap enemyMap;
        private GameState state;


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
        public GameBuilder buildPlayer(int number){
            if (number ==1){
                this.player = PlayerFactory.getPlyer("human");
            }else{
                this.player = PlayerFactory.getPlyer("computer");
            }


            return this;
        }
        public GameBuilder buildMyMap(){
            this.myMap = new PlayerMap();
            myMap.loadMap();
            return this;
        }
        public GameBuilder buildEnemyMap(){
            this.enemyMap = new EnemyMap();
            enemyMap.loadMap();
            return this;
        }
        public GameBuilder buildState(){
            this.state = new EmptyState();
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


            return game;
        }


    }


}
