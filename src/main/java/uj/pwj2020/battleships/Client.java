package uj.pwj2020.battleships;

import uj.pwj2020.battleships.inputReceiver.ScannerLineReceiver;
import uj.pwj2020.battleships.inputReceiver.InputReceiver;
import uj.pwj2020.battleships.states.StartGame;
import uj.pwj2020.battleships.view.CommandLineView;
import uj.pwj2020.battleships.view.GameView;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
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
            GameView view = CommandLineView.getInstance();
            InputReceiver receiver = ScannerLineReceiver.getInstance(new Scanner(System.in));
            InetAddress HOST = Util.getHost(view, receiver);
            var gameParameters = Util.getGameParameters(view, receiver);
            Socket s = new Socket(HOST, port);
            view.showMessage("Połączono, wykonaj ruch");
            Game game = Game.builder()
                    .buildIn(s)
                    .buildOut(s)
                    .buildPlayer(gameParameters.get("playerType"), gameParameters.get("mode"))
                    .buildMyMap()
                    .buildEnemyMap()
                    .buildState()
                    .buildView(view)
                    .builReceiver(receiver)
                    .gameNotOver()
                    .buid();
            game.setState(new StartGame(game));
            game.playGame();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
