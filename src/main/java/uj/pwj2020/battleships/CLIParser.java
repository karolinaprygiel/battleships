package uj.pwj2020.battleships;

import java.nio.file.Path;

public class CLIParser {
    private final String[] args;
    private String mode;
    private int port;
    private Path mapPath;

    public CLIParser(String[] args) {
        this.args = args;
        parseArguments();
    }

    private void parseArguments() {
        if (args.length != 6){
            throw new IllegalArgumentException("Wrong number of arguments");
        }
        for (int i = 0; i < args.length; i++){
            switch (args[i]) {
                case "-mode" -> this.mode = args[++i].toLowerCase();
                case "-port" -> this.port = Integer.parseInt(args[++i]);
                case "-map" -> this.mapPath = Path.of(args[++i]);
                default -> throw new IllegalArgumentException("Wrong argument: " + args[i]);
            }
        }
    }

    public String getMode() {
        return mode;
    }

    public int getPort() {
        return port;
    }

    public Path getMapPath() {
        return mapPath;
    }
}