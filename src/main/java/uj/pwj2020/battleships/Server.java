package uj.pwj2020.battleships;


import uj.pwj2020.battleships.inputReceiver.CommandLineReceiver;
import uj.pwj2020.battleships.inputReceiver.InputReceiver;
import uj.pwj2020.battleships.states.GetResponse;
import uj.pwj2020.battleships.view.CommandLineView;
import uj.pwj2020.battleships.view.GameView;

import java.io.IOException;
import java.net.*;
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

            InetAddress addr = Util.findAddress();
            GameView view = new CommandLineView();
            InputReceiver receiver = new CommandLineReceiver(new Scanner(System.in));
            ServerSocket serverSocket = new ServerSocket(port, 10000, addr);
            view.showMessage("Server started at: " + addr + " on port " + port);
            var gameParameters = Util.getGameParameters(view, receiver);
            Socket socket = serverSocket.accept();
            view.showMessage("Got request from " + socket.getRemoteSocketAddress() + ", starting session ");
            Game game = Game.builder()
                    .buildIn(socket)
                    .buildOut(socket)
                    .buildPlayer(gameParameters.get("playerType"), gameParameters.get("mode"))
                    .buildMyMap()
                    .buildEnemyMap()
                    .buildState()
                    .buildView(view)
                    .builReceiver(receiver)
                    .buid();
            game.setState(new GetResponse(game));
            game.playGame();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
