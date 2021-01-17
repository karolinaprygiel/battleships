package uj.pwj2020.battleships.players;

public class PlayerFactory {
    public static Player getPlyer(String playerType, String mode) {

        if (playerType.equalsIgnoreCase("bot")) {
            Bot bot = new Bot();
            if (mode.equalsIgnoreCase("E"))
                bot.setMode(new EasyMode());
            else if (mode.equalsIgnoreCase("H")){
                bot.setMode(new HardMode());
            }else {
                bot.setMode(new EmptyMode());
            }
            return bot;

        } else if (playerType.equalsIgnoreCase("human")) {
            return new Human();
        }
        return new EmptyPlayer();
    }
}
