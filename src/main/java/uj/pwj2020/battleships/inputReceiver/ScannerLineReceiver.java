package uj.pwj2020.battleships.inputReceiver;

import java.util.Scanner;

public class ScannerLineReceiver implements InputReceiver {
    private static ScannerLineReceiver instance;
    private final Scanner scanner;


    private ScannerLineReceiver(Scanner scanner)  {

        this.scanner = scanner;

    }
    public static ScannerLineReceiver getInstance(Scanner scanner)  {
        if (instance == null) {
            instance = new ScannerLineReceiver(scanner);
        }
        return instance;
    }

    @Override
    public String receive() {
        return scanner.next();

    }
}
