package roboVac;

import java.util.Random;

public class MoveRandomly implements MoveBehaviour {
    private Direction direction;

    @Override
    public void init() {
        direction = Direction.getRandomDirection();
    }

    @Override
    public void move(RoboVac roboVac) {
        var room = roboVac.getRoom();

        int x, y;

        do {
            x = room.getRobotPosX() + direction.getX();
            y = room.getRobotPosY() + direction.getY();

            if (room.getStatus(x, y) == Status.WALL || new Random().nextInt(0, 10) == 0) {
                init();
            }
        } while (room.getStatus(x, y) == Status.WALL);

        room.setRobot(x, y);
    }
}
