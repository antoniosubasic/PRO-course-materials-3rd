package roboVac;

import java.util.Random;

public enum Direction {
    NORTH(0, -1),
    EAST(1, 0),
    SOUTH(0, 1),
    WEST(-1, 0);

    private final int x;
    private final int y;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isVertical() {
        return this == Direction.NORTH || this == Direction.SOUTH;
    }

    public boolean isHorizontal() {
        return this == Direction.EAST || this == Direction.WEST;
    }

    public static Direction getRandomDirection() {
        var directions = values();
        return directions[new Random().nextInt(directions.length)];
    }
}
