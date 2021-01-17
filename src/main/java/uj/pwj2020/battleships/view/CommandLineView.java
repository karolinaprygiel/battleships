package uj.pwj2020.battleships.view;

import uj.pwj2020.battleships.map.Map;

public class CommandLineView implements GameView {
    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void showMap(Map map) {
        System.out.println(map);

    }
}
