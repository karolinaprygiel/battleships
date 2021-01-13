package uj.pwj2020.battleships;

import uj.pwj2020.battleships.map.Map;
import uj.pwj2020.battleships.players.Player;
import uj.pwj2020.battleships.players.PlayerFactory;
import uj.pwj2020.battleships.states.EmptyState;
import uj.pwj2020.battleships.states.GameState;

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

    public Map getMyMap() {
        return myMap;
    }

    public Map getEnemyMap() {
        return enemyMap;
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

            this.player = PlayerFactory.getPlyer(s);
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
