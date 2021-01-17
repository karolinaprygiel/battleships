package uj.pwj2020.battleships.inputReceiver;

import java.util.Scanner;

public class CommandLineReceiver implements InputReceiver {
    Scanner scanner;

    public CommandLineReceiver(Scanner scanner) {
        this.scanner = scanner;

    }

    @Override
    public String receive() {
        return scanner.next();

    }
}
