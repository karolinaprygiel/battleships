package uj.pwj2020.battleships.generator;

public interface BattleshipGenerator {

    String generateMap();


    static BattleshipGenerator getInstance() {
        return new RandomBattleshipGenerator();
    }


}
