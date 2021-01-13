package uj.pwj2020.battleships;

import uj.pwj2020.battleships.states.StartGame;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.file.Path;
import java.util.Scanner;

public class Client {

    private static Client instance;
    int port;
    Path mapPath;

    private Client(int port, Path mapPath)  {

        this.port = port;
        this.mapPath = mapPath;

    }

    public static Client getInstance(int port, Path mapPath)  {
        if (instance == null) {
            instance = new Client(port, mapPath);
        }
        return instance;
    }


    public void play()  {
        try {
            InetAddress HOST;
            Scanner in = new Scanner(System.in);
            System.out.println("Czy chcesz podać adres serwera? [T/N]");
            String response = in.next();
            if (response.equalsIgnoreCase("T")){
                System.out.println("Podaj adres serwera");
                String address = in.next();
                HOST = InetAddress.getByName(address);
            }else{

                HOST= Util.findAddress();
            }
            Socket s = new Socket(HOST, port);
            System.out.println("Connected, make move");
            Game game = Game.builder()
                    .buildIn(s)
                    .buildOut(s)
                    .buildPlayer("computer")
                    .buildMyMap(mapPath)
                    .buildEnemyMap()
                    .buildState()
                    .buid();
            game.setState(new StartGame(game));
            game.playGame();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
