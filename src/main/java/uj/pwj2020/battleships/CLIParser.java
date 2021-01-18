package uj.pwj2020.battleships;

import java.nio.file.Path;

public class CLIParser {
    private final String[] args;
    private String mode;
    private int port;

    public CLIParser(String[] args) {
        this.args = args;
        parseArguments();
    }

    private void parseArguments() {

        for (int i = 0; i < args.length; i++){
            switch (args[i]) {
                case "-mode" -> this.mode = args[++i].toLowerCase();
                case "-port" -> this.port = Integer.parseInt(args[++i]);
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

}
