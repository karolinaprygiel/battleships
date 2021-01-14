package uj.pwj2020.battleships.map;

public interface MapIterator {
    boolean hasNext();
    Cell getNext();
    Cell getRandom();
}
