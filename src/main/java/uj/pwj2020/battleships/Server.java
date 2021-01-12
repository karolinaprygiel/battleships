package uj.pwj2020.battleships;


import java.io.IOException;
import java.net.*;
import java.nio.file.Path;

public class Server {
    public static void play(int port, Path mapPath) {
        try {
            InetAddress addr = Util.findAddress();
            ServerSocket serverSocket = new ServerSocket(port, 10000, addr);
            System.out.println("Server started at: " + addr + " on port " + port);
            Socket socket = serverSocket.accept();
            System.out.println("Got request from " + socket.getRemoteSocketAddress() + ", starting session ");
            Game game = Game.builder()
                    .buildIn(socket)
                    .buildOut(socket)
                    .buildPlayer("human")
                    .buildMyMap(mapPath)
                    .buildEnemyMap()
                    .buildState(GameState.GETRESPONSE)
                    .buildMessage()
                    .buildLastMove()
                    .buildEnemyLastMessage()
                    .buildInvalidCounter()
                    .buid();
            game.playGame();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
