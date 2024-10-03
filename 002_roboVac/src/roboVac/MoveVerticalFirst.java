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
    public void move(RoboVac roboVac) {
        var room = roboVac.getRoom();

        int x, y;

        do {
            x = room.getRobotPosX() + direction.getX();
            y = room.getRobotPosY() + direction.getY();

            if (room.getStatus(x, y) == Status.WALL) {
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
        } while (room.getStatus(x, y) == Status.WALL);

        room.setRobot(x, y);
    }
}
