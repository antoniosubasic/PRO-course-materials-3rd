import roboVac.RoboVac;
import roboVac.Room;

public class RoboVacApp {
    public static void main(String[] args) {
        var myRoboVac = new RoboVac("RoboVac EG");

        myRoboVac.setRoom(new Room(new String[] {
                "######",
                "#    #",
                "#    #",
                "#    #",
                "######"
        }));
        myRoboVac.setPosition(1, 1);
        myRoboVac.SetMoveBehaviour(new roboVac.MoveVerticalFirst());

        myRoboVac.clean();
        myRoboVac.clean();
    }
}
