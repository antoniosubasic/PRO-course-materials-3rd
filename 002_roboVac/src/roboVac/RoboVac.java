package roboVac;

public class RoboVac {
    private String name;
    private Room room;
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

    public void setPosition(int posX, int posY) {
        room.setRobot(posX, posY);
    }

    public void printRoomStatus() {
        System.out.println(room);
    }

    public void SetMoveBehaviour(MoveBehaviour moveBehaviour) {
        this.moveBehaviour = moveBehaviour;
    }

    public void clean() {
        room.setAllDirty();

        room.setCleanAtRobotPosition();
        printRoomStatus();

        moveBehaviour.init();

        do {
            moveBehaviour.move(this);
            room.setCleanAtRobotPosition();
            printRoomStatus();
        } while (!room.isClean());
    }
}
