package roboVac;

public class MoveToTarget implements MoveBehaviour {
    private RoboVac roboVac;
    private Position target;
    private int[][] distanceMatrix;

    public MoveToTarget(RoboVac roboVac, Position target) {
        this.roboVac = roboVac;
        this.target = target;
    }

    @Override
    public void init() {
        this.distanceMatrix = roboVac.getRoom().getDistanceMatrix(target);
    }

    public void setTarget(Position target) {
        this.target = target;
        init();
    }

    @Override
    public Position getNextMove() {
        if (roboVac.getPosition().equals(target)) {
            return null;
        }

        var neighbours = roboVac.getPosition().getNeighbours();
        Position pos = null;

        for (var neighbour : neighbours) {
            if (distanceMatrix[neighbour.y][neighbour.x] != -1
                    && (pos == null || distanceMatrix[neighbour.y][neighbour.x] < distanceMatrix[pos.y][pos.x])) {
                pos = neighbour;
            }
        }

        return pos;
    }
}
