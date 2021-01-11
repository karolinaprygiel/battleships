package uj.pwj2020.battleships.map;

public enum CellType {
    SHIP('#'),
    WATER('.'),
    UNKNOWN('?'),
    HIT('@'),
    MISS('~');

    private final char symbol;

    CellType(char symbol){
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }
    public static CellType valueOf(char symbol) {
        for (CellType type : values()) {
            if (type.symbol == symbol) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown symbol: " + symbol);
    }
}
