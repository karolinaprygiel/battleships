package uj.pwj2020.battleships.players;

import java.util.Scanner;

public class Human implements Player{
    @Override
    public String hitField() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();

    }
}
