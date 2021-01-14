package uj.pwj2020.battleships;


import uj.pwj2020.battleships.states.GetResponse;

import java.io.IOException;
import java.net.*;
import java.nio.file.Path;
import java.util.Scanner;

public class Server {
    private static Server instance;
    int port;


    private Server(int port)  {

        this.port = port;

    }

    public static Server getInstance(int port)  {
        if (instance == null) {
            instance = new Server(port);
        }
        return instance;
    }

    public void play() {
        try {
            Scanner in = new Scanner(System.in);
            System.out.println("Wybierz tryb");
            System.out.println("1 - wykonujesz ruchy sam");
            System.out.println("2 - wykonuje je za Ciebie bot");
            int number = in.nextInt();


            InetAddress addr = Util.findAddress();
            ServerSocket serverSocket = new ServerSocket(port, 10000, addr);
            System.out.println("Server started at: " + addr + " on port " + port);
            Socket socket = serverSocket.accept();
            System.out.println("Got request from " + socket.getRemoteSocketAddress() + ", starting session ");
            Game game = Game.builder()
                    .buildIn(socket)
                    .buildOut(socket)
                    .buildPlayer(number)
                    .buildMyMap()
                    .buildEnemyMap()
                    .buildState()
                    .buid();
            game.setState(new GetResponse(game));
            game.playGame();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
