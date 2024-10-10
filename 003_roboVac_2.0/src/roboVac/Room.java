package roboVac;

public class Room {
    private Status[][] layout;
    private int robotPosX;
    private int robotPosY;

    public Room(String[] layout) {
        this.layout = new Status[layout.length][layout[0].length()];

        for (var y = 0; y < this.layout.length; y++) {
            for (var x = 0; x < this.layout[y].length; x++) {
                this.layout[y][x] = Status.valueOfLabel(layout[y].charAt(x));
            }
        }
    }

    public void setRobot(int posX, int posY) {
        robotPosX = posX;
        robotPosY = posY;
    }

    public int getRobotPosX() {
        return robotPosX;
    }

    public int getRobotPosY() {
        return robotPosY;
    }

    public void setCleanAtRobotPosition() {
        layout[robotPosY][robotPosX] = Status.CLEAN;
    }

    public void setAllDirty() {
        for (var row : layout) {
            for (int x = 0; x < row.length; x++) {
                if (row[x] != Status.WALL) {
                    row[x] = Status.DIRTY;
                }
            }
        }
    }

    public Status getStatus(int posX, int posY) {
        return layout[posY][posX];
    }

    public boolean isClean() {
        for (var row : layout) {
            for (var cell : row) {
                if (cell == Status.DIRTY) {
                    return false;
                }
            }
        }

        return true;
    }

    public String toString() {
        var sb = new StringBuilder();

        for (var y = 0; y < layout.length; y++) {
            for (var x = 0; x < layout[y].length; x++) {
                sb.append(x == robotPosX && y == robotPosY ? 'R' : layout[y][x].label);
            }

            sb.append('\n');
        }

        return sb.toString();
    }
}
