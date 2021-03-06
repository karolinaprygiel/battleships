package uj.pwj2020.battleships;
import java.io.IOException;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {

        CLIParser parser = new CLIParser(args);
        String mode = parser.getMode();
        int port = parser.getPort();


        if (mode.equals("client")) {
            Client client = Client.getInstance(port);
            client.play();
        } else if (mode.equals("server")) {
            Server server = Server.getInstance(port);
            server.play();

        }


    }
}
