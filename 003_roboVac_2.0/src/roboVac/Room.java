package roboVac;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Room {
    private Layout layout;
    private Position robotPos;

    public Room(String[] layout) {
        this.layout = new Layout(layout);
    }

    public boolean isWall(Position pos) {
        return layout.getStatus(pos) == Status.WALL;
    }

    public boolean isDirty(Position pos) {
        return layout.getStatus(pos) == Status.DIRTY;
    }

    public boolean isClean(Position pos) {
        return layout.getStatus(pos) == Status.CLEAN;
    }

    public boolean isAccessible(Position pos) {
        return layout.isValid(pos) && !isWall(pos);
    }

    public boolean isClean() {
        for (var pos : layout.getAllPosition()) {
            if (layout.getStatus(pos) == Status.DIRTY) {
                return false;
            }
        }

        return true;
    }

    public void setAllDirty() {
        for (var pos : layout.getAllPosition()) {
            layout.setStatus(pos, Status.DIRTY);
        }
    }

    public Position getRobotPosition() {
        return robotPos;
    }

    public void setRobotPosition(Position pos) {
        robotPos = pos;
    }

    public void setCleanAtRobotPosition() {
        layout.setStatus(robotPos, Status.CLEAN);
    }

    public int[][] getDistanceMatrix(Position pos) {
        var distances = new int[layout.getHeight()][layout.getWidth()];

        for (var y = 0; y < distances.length; y++) {
            for (var x = 0; x < distances[0].length; x++) {
                distances[y][x] = -1;
            }
        }

        distances[pos.y][pos.x] = 0;
        Queue<Position> processingQueue = new LinkedList<>();

        while (pos != null) {
            for (var neighbour : pos.getNeighbours()) {
                if (isAccessible(neighbour) && distances[neighbour.y][neighbour.x] == -1) {
                    distances[neighbour.y][neighbour.x] = distances[pos.y][pos.x] + 1;
                    processingQueue.add(neighbour);
                }
            }

            pos = processingQueue.poll();
        }

        return distances;
    }

    public Position getNearestDirtyPosition(Position pos) {
        Position nearestDirtyPosition = null;
        var distances = getDistanceMatrix(pos);

        for (var y = 0; y < distances.length; y++) {
            for (var x = 0; x < distances[0].length; x++) {
                var position = new Position(x, y);

                if (layout.getStatus(position) == Status.DIRTY && (nearestDirtyPosition == null
                        || pos.getDistance(position) < pos.getDistance(nearestDirtyPosition))) {
                    nearestDirtyPosition = position;
                }
            }
        }

        return nearestDirtyPosition;
    }

    public String getLayout(Position roboVacPosition) {
        return layout.toString(roboVacPosition);
    }

    public void printDistanceMatrix() {
        var distances = getDistanceMatrix(robotPos);

        for (var y = 0; y < distances.length; y++) {
            for (var x = 0; x < distances[0].length; x++) {
                System.out.printf("%3d, ", distances[y][x]);
            }

            System.out.println();
        }
    }

    private class Layout {
        private Status[][] layout;

        public Layout(String[] layout) {
            this.layout = new Status[layout.length][layout[0].length()];

            for (var y = 0; y < this.layout.length; y++) {
                for (var x = 0; x < this.layout[0].length; x++) {
                    this.layout[y][x] = Status.valueOfLabel(layout[y].charAt(x));
                }
            }
        }

        public int getHeight() {
            return layout.length;
        }

        public int getWidth() {
            return layout[0].length;
        }

        public Status getStatus(Position position) {
            return layout[position.y][position.x];
        }

        public void setStatus(Position position, Status status) {
            layout[position.y][position.x] = status;
        }

        public boolean isValid(Position position) {
            return position.y >= 0 && position.y < layout.length && position.x >= 0
                    && position.x < layout[position.y].length;
        }

        public Position[] getAllPosition() {
            List<Position> positions = new ArrayList<>();

            for (int y = 0; y < layout.length; y++) {
                for (int x = 0; x < layout[y].length; x++) {
                    if (layout[y][x] != Status.WALL) {
                        positions.add(new Position(x, y));
                    }
                }
            }

            return positions.toArray(new Position[0]);
        }

        public String toString(Position roboVacPosition) {
            var sb = new StringBuilder();

            for (var y = 0; y < layout.length; y++) {
                for (var x = 0; x < layout[y].length; x++) {
                    sb.append(y == roboVacPosition.y && x == roboVacPosition.x ? 'R' : layout[y][x].label);
                }

                sb.append('\n');
            }

            return sb.toString();
        }
    }
}
