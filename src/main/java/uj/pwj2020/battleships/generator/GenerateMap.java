package uj.pwj2020.battleships.generator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class GenerateMap {
    public static void main(String[] args) {
        BattleshipGenerator generator = BattleshipGenerator.getInstance();
        String[] map = generator.generateMap();
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the name for the map");
        String fileName = in.next();

        writeMapToFile(fileName, map);

    }

    private static void writeMapToFile(String fileName, String[] map) {
        try (BufferedWriter br = new BufferedWriter(new FileWriter(new File(fileName)));){
            for (int i = 0; i < 10; i++){
                br.write(map[i]);
                br.newLine();
            }
            System.out.println("Generated sucesfully");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
