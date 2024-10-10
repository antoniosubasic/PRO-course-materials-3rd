package roboVac;

public class MoveVerticalFirst implements MoveBehaviour {
    private Direction direction;
    private Direction horizontal;
    private Direction vertical;

    @Override
    public void init() {
        direction = vertical = Direction.SOUTH;
        horizontal = Direction.EAST;
    }

    @Override
    public Position getNextMove(RoboVac roboVac) {
        var room = roboVac.getRoom();

        var pos = new Position(0, 0);

        do {
            pos.x = room.getRobotPosition().x + direction.getX();
            pos.y = room.getRobotPosition().y + direction.getY();

            if (room.getStatus(pos) == Status.WALL) {
                if (direction.isHorizontal()) {
                    horizontal = (horizontal == Direction.EAST ? Direction.WEST : Direction.EAST);
                    direction = vertical;
                } else {
                    direction = horizontal;
                }
            } else if (direction.isHorizontal()) {
                horizontal = direction;
                direction = vertical = (vertical == Direction.NORTH ? Direction.SOUTH : Direction.NORTH);
            }
        } while (room.getStatus(pos) == Status.WALL);

        return pos;
    }
}
