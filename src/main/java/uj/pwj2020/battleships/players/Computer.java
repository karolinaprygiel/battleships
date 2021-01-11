package uj.pwj2020.battleships.players;
import java.util.Random;

public class Computer implements Player {
    @Override
    public String hitField() {
        Random random = new Random();
        char column = (char) (random.nextInt(10) + 'A');
        int row = random.nextInt(10) + 1;

        return column + String.valueOf(row);

    }
}
