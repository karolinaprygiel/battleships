package uj.pwj2020.battleships.players;

import uj.pwj2020.battleships.map.Map;

import java.util.Scanner;

public class Human implements Player{
    @Override
    public String hitField(Map map) {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();

    }
}
