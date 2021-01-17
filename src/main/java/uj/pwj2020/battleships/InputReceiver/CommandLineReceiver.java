package uj.pwj2020.battleships.InputReceiver;

import view.CommandLineView;

import java.util.Scanner;

public class CommandLineReceiver implements InputReceiver {
    Scanner scanner;

    public CommandLineReceiver() {
        this.scanner = new Scanner(System.in);

    }

    @Override
    public String receive() {
        return scanner.next();

    }
}
