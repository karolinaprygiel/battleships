package uj.pwj2020.battleships.players;

public class PlayerFactory {
    public static Player getPlyer(String playerType) {

        if (playerType.equalsIgnoreCase("computer")) {
            return new Computer();
        } else if (playerType.equalsIgnoreCase("human")) {
            return new Human();
        }
        return new EmptyPlayer();
    }
}
