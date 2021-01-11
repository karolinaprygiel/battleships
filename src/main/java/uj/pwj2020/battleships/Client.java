package uj.pwj2020.battleships;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.file.Path;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Client {


    public static void play(int port, Path mapPath)  {
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
            Game game = new Game(s, GameState.STARTGAME, mapPath);
            game.playGame();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
