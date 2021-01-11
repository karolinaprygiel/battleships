package uj.pwj2020.battleships.generator;

import java.util.*;

public class RandomBattleshipGenerator implements BattleshipGenerator {
    @Override
    public String generateMap() {

        StringBuilder[] Matrix = initializeMatrix();


        putMastedShip(Matrix, 4);

        for (int i = 0; i < 2; i++) {
            putMastedShip(Matrix, 3);
        }
        for (int i = 0; i < 3; i++) {
            putMastedShip(Matrix, 2);
        }
        for (int i = 0; i < 4; i++) {
            putMastedShip(Matrix, 1);
        }

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
        return result.toString().replace('?', '.');
    }

    private void putMastedShip(StringBuilder[] Matrix, int numOfMasts) {
        
        LinkedList<Point> reservedPoints = null;
        boolean canPutShipFromThisPoint = false;
        
        while (!canPutShipFromThisPoint) {
            reservedPoints = new LinkedList<>();
            Point currPoint = setInitialPoint(Matrix);
            reservedPoints.add(currPoint);
            canPutShipFromThisPoint = appendMast(Matrix, numOfMasts - 1, reservedPoints);
            if (!canPutShipFromThisPoint) {
                removePointFromMap(currPoint, Matrix);
            }
            
        }
        surroundMast(reservedPoints, Matrix);
    }

    private boolean appendMast(StringBuilder[] Matrix, int num, LinkedList<Point> points) {
        if (num == 0) {
            return true;
        }
        Point currPoint = points.getLast();
        List<String> directionsList = Arrays.asList("UP", "DOWN", "LEFT", "RIGHT");
        Collections.shuffle(directionsList);

        for (String direction : directionsList) {
            Point nextPoint = tryToMove(currPoint, direction, Matrix);
            if (nextPoint != null) {
                points.add(nextPoint);
                boolean canAppendFromThisPoint = appendMast(Matrix, num - 1, points);
                if (canAppendFromThisPoint) {
                    return true;
                } else {
                    points.removeLast();
                    removePointFromMap(nextPoint, Matrix);

                }

            }
        }
        return false;

    }

    private void removePointFromMap(Point point, StringBuilder[] Matrix) {
        Matrix[point.getX()].setCharAt(point.getY(), '.');
    }

    private void markAsReserved(Point point, StringBuilder[] Matrix) {
        Matrix[point.getX()].setCharAt(point.getY(), '#');
    }

    private void surroundMast(LinkedList<Point> points, StringBuilder[] Matrix) {
        for (Point point : points) {
            int x = point.getX();
            int y = point.getY();

            if (x + 1 < 10 && Matrix[x + 1].charAt(y) != '#') {
                Matrix[x + 1].setCharAt(y, '?');
            }
            if (x - 1 >= 0 && Matrix[x - 1].charAt(y) != '#') {
                Matrix[x - 1].setCharAt(y, '?');
            }
            if (y + 1 < 10 && Matrix[x].charAt(y + 1) != '#') {
                Matrix[x].setCharAt(y + 1, '?');
            }
            if (y - 1 >= 0 && Matrix[x].charAt(y - 1) != '#') {
                Matrix[x].setCharAt(y - 1, '?');
            }
            if (x + 1 < 10 && y + 1 < 10 && Matrix[x + 1].charAt(y + 1) != '#') {
                Matrix[x + 1].setCharAt(y + 1, '?');
            }
            if (x - 1 >= 0 && y + 1 < 10 && Matrix[x - 1].charAt(y + 1) != '#') {
                Matrix[x - 1].setCharAt(y + 1, '?');
            }
            if (x + 1 < 10 && y - 1 >= 0 && Matrix[x + 1].charAt(y - 1) != '#') {
                Matrix[x + 1].setCharAt(y - 1, '?');
            }
            if (x - 1 >= 0 && y - 1 >= 0 && Matrix[x - 1].charAt(y - 1) != '#') {
                Matrix[x - 1].setCharAt(y - 1, '?');
            }

        }
    }

    private Point setInitialPoint(StringBuilder[] Matrix) {
        Point initialPoint;
        int x, y;
        do {
            initialPoint = new Point();
            x = initialPoint.getX();
            y = initialPoint.getY();
        } while (Matrix[x].charAt(y) != '.');

        markAsReserved(initialPoint, Matrix);
        return initialPoint;
    }

    private Point tryToMove(Point currPoint, String direction, StringBuilder[] matrix) {
        int currX = currPoint.getX();
        int currY = currPoint.getY();

        switch (direction) {
            case "UP":
                currX -= 1;
                if (currX < 0) {
                    return null;
                }
                break;
            case "DOWN":
                currX += 1;
                if (currX > 9) {
                    return null;
                }
                break;
            case "LEFT":
                currY -= 1;
                if (currY < 0) {
                    return null;
                }
                break;
            case "RIGHT":
                currY += 1;
                if (currY > 9) {
                    return null;
                }
        }
         if (matrix[currX].charAt(currY) != '.'){
             return null;
         }

        Point nextPoint = new Point(currX, currY);
        markAsReserved(nextPoint, matrix);
        return nextPoint;
    }


    private class Point {
        private int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Point() {
            Random r = new Random();
            this.x = r.nextInt(10);
            this.y = r.nextInt(10);
        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }


    }


}



