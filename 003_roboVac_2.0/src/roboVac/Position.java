package roboVac;

public class Position {
    public int x;
    public int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position[] getNeighbours() {
        return new Position[] {
                new Position(x - 1, y),
                new Position(x + 1, y),
                new Position(x, y - 1),
                new Position(x, y + 1)
        };
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Position) {
            var pos = (Position) obj;
            return pos.x == this.x && pos.y == this.y;
        }

        return false;
    }
}
