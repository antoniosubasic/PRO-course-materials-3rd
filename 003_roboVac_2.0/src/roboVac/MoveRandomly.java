package roboVac;

import java.util.Random;

public class MoveRandomly implements MoveBehaviour {
    private Direction direction;

    @Override
    public void init() {
        direction = Direction.getRandomDirection();
    }

    @Override
    public Position getNextMove(RoboVac roboVac) {
        var room = roboVac.getRoom();

        var pos = new Position(0, 0);

        do {
            pos.x = room.getRobotPosition().x + direction.getX();
            pos.y = room.getRobotPosition().y + direction.getY();

            if (room.getStatus(pos) == Status.WALL || new Random().nextInt(0, 10) == 0) {
                init();
            }
        } while (room.getStatus(pos) == Status.WALL);

        return pos;
    }
}
