package roboVac;

public class RoboVac {
    private String name;
    private Room room;
    private int moveCount;
    private MoveBehaviour moveBehaviour;

    public RoboVac(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Position getPosition() {
        return room.getRobotPosition();
    }

    public int getMoveCount() {
        return moveCount;
    }

    public void setPosition(Position pos) {
        room.setRobotPosition(pos);
    }

    public void printRoomStatus() {
        System.out.println(room.getLayout(this.getPosition()));
    }

    public void SetMoveBehaviour(MoveBehaviour moveBehaviour) {
        this.moveBehaviour = moveBehaviour;
    }

    public void clean() {
        room.setAllDirty();
        resetMoveCount();

        room.setCleanAtRobotPosition();
        printRoomStatus();

        moveBehaviour = new MoveVerticalFirst(this);
        moveBehaviour.init();

        while (!room.isClean()) {
            var nextPos = moveBehaviour.getNextMove();
            if (room.isClean(nextPos)) {
                moveToTarget(room.getNearestDirtyPosition(room.getRobotPosition()));
            } else {
                setPosition(nextPos);
                increaseMoveCount();
            }

            room.setCleanAtRobotPosition();
            printRoomStatus();
        }
    }

    private void moveToTarget(Position target) {
        var moveToTarget = new MoveToTarget(this, target);
        moveToTarget.init();

        Position next;

        while ((next = moveToTarget.getNextMove()) != null) {
            setPosition(next);
            increaseMoveCount();
        }
    }

    private void resetMoveCount() {
        moveCount = 0;
    }

    private void increaseMoveCount() {
        moveCount++;
    }
}
