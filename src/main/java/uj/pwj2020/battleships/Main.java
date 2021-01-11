package uj.pwj2020.battleships;
import java.io.IOException;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {


        CLIParser parser = new CLIParser(args);
        String mode = parser.getMode();
        int port = parser.getPort();
        Path mapPath = parser.getMapPath();

        if (mode.equals("client")){
            Client.play(port, mapPath);
        }else if(mode.equals("server")){
            Server.play(port, mapPath);
        }else if(mode.equals("serverTest")){

        }else if(mode.equals("clientTest")){

        }

    }



}
