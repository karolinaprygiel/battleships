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

    private Client(int port)  {

        this.port = port;

    }

    public static Client getInstance(int port)  {
        if (instance == null) {
            instance = new Client(port);
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
            }else if(response.equalsIgnoreCase("M")) {
                HOST = InetAddress.getByName("192.168.137.236");
            }else{
                HOST= Util.findAddress();
            }

            System.out.println("Wybierz tryb");
            System.out.println("1 - wykonujesz ruchy sam");
            System.out.println("2 - wykonuje je za Ciebie bot");
            int number = in.nextInt();



            Socket s = new Socket(HOST, port);
            System.out.println("Connected, make move");
            Game game = Game.builder()
                    .buildIn(s)
                    .buildOut(s)
                    .buildPlayer(number)
                    .buildMyMap()
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
