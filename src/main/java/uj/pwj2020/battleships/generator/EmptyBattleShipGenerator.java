package uj.pwj2020.battleships.generator;

public class EmptyBattleShipGenerator implements BattleshipGenerator {
    @Override
    public String generateMap() {
        StringBuilder[] Matrix = initializeMatrix();
       return prepareResultString(Matrix);
    }
    private StringBuilder[] initializeMatrix() {
        StringBuilder[] matrix = new StringBuilder[10];

        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = new StringBuilder("..........");


        }
        return matrix;
    }
    private String prepareResultString(StringBuilder[] Matrix) {
        StringBuilder result = new StringBuilder();
        for (StringBuilder matrix : Matrix) {
            result.append(matrix);
        }
        return result.toString();
    }
}
