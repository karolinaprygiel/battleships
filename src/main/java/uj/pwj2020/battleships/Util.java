package uj.pwj2020.battleships;

import uj.pwj2020.battleships.inputReceiver.InputReceiver;
import uj.pwj2020.battleships.view.GameView;

import java.net.*;
import java.util.HashMap;

public class Util {
    static InetAddress findAddress() throws SocketException, UnknownHostException {
        var en0 = NetworkInterface.getByName("en0");
        return en0.inetAddresses()
                .filter(a -> a instanceof Inet4Address)
                .findFirst()
                .orElse(InetAddress.getLocalHost());

    }


    static InetAddress getHost(GameView view, InputReceiver receiver){
       InetAddress HOST = null;

        view.showMessage("Czy chcesz podać adres serwera? [T/N]");
        String response = receiver.receive();

        try {
            if (response.equalsIgnoreCase("T")) {
                view.showMessage("Podaj adres serwera");
                String address = receiver.receive();
                HOST = InetAddress.getByName(address);
            } else if (response.equalsIgnoreCase("M")) {
                HOST = InetAddress.getByName("192.168.137.236");
            } else {
                HOST = Util.findAddress();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return HOST;

    }

    static HashMap<String, String> getGameParameters(GameView view, InputReceiver receiver) {

        HashMap<String, String> gameParameters = new HashMap<>();
        view.showMessage("Wybierz tryb");
        view.showMessage("1 - wykonujesz ruchy sam");
        view.showMessage("2 - wykonuje je za Ciebie bot");
        String number = receiver.receive();
        String playerType = (number.equals("1") ? "human" : "bot");
        gameParameters.put("playerType", playerType);


        String choice = "";
        if (playerType.equalsIgnoreCase("bot")) {

            view.showMessage("Wybierz poziom trudności");
            view.showMessage("E - łatwy");
            view.showMessage("H - trudny");
            choice = receiver.receive();
            gameParameters.put("mode", choice);
        }
        return gameParameters;
    }
}
