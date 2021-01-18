package uj.pwj2020.battleships.view;

import uj.pwj2020.battleships.Client;
import uj.pwj2020.battleships.map.Map;

public class CommandLineView implements GameView {
    private static CommandLineView instance;

    private CommandLineView()  {

    }
    public static CommandLineView getInstance()  {
        if (instance == null) {
            instance = new CommandLineView();
        }
        return instance;
    }
    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void showMap(Map map) {
        System.out.println(map);

    }
}
