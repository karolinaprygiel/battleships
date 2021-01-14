package uj.pwj2020.battleships.players;

import java.util.Scanner;

public class PlayerFactory {
    public static Player getPlyer(String playerType) {

        if (playerType.equalsIgnoreCase("computer")) {
            Scanner in = new Scanner(System.in);
            System.out.println("Wybierz poziom trudności");
            System.out.println("E - łatwy");
            System.out.println("H - trudny");
            String choice = in.next();
            Bot bot =  new Bot();
            if (choice.equals("E")){
                bot.setMode(new EasyMode());
            }else {
                bot.setMode(new HardMode());
            }
           return bot;

        } else if (playerType.equalsIgnoreCase("human")) {
            return new Human();
        }
        return new EmptyPlayer();
    }
}
