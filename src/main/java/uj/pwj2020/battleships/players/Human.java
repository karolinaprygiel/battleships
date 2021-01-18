package uj.pwj2020.battleships.players;

import uj.pwj2020.battleships.inputReceiver.ScannerLineReceiver;
import uj.pwj2020.battleships.inputReceiver.InputReceiver;
import uj.pwj2020.battleships.map.Map;

import java.util.Scanner;

public class Human implements Player{
    @Override
    public String hitField(Map map) {
        InputReceiver receiver = ScannerLineReceiver.getInstance(new Scanner(System.in));
        return receiver.receive();

    }
}
