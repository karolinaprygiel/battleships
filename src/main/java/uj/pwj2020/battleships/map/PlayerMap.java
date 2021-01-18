package uj.pwj2020.battleships.map;

import uj.pwj2020.battleships.Client;
import uj.pwj2020.battleships.generator.BattleshipGenerator;


public class PlayerMap extends Map {
    private static PlayerMap instance;

    private PlayerMap()  {

    }
    public static PlayerMap getInstance()  {
        if (instance == null) {
            instance = new PlayerMap();
        }
        return instance;
    }

    @Override
    public void loadMap() {
        BattleshipGenerator gen = BattleshipGenerator.getInstance();
        String[] generatedMap = gen.generateMap();
        Cell[][] loadedMap = new Cell[10][10];
        for (int i = 0; i <10; i++){
            for (int j = 0; j < 10; j++){
                char symbol = generatedMap[i].charAt(j);
                loadedMap[i][j] = new Cell(i, j, symbol);
            }
        }
        this.map = loadedMap;
        loadShips();

    }

    @Override
    public MapIterator iterator() {
        return new PlayerMapIterator(this);
    }


    public void loadShips(){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (map[i][j].getType() == CellType.SHIP && map[i][j].getShip() == null){
                    Ship ship = new Ship();
                    buildShip(i,j,ship);

                }
            }
        }
    }




}
