import roboVac.Position;
import roboVac.RoboVac;
import roboVac.Room;

public class RoboVacApp {
    public static void main(String[] args) {
        var myRoboVac = new RoboVac("RoboVac EG");

        myRoboVac.setRoom(new Room(new String[] {
                "###################",
                "#                 #",
                "#                 #",
                "#      ########   #",
                "#      #          #",
                "#      #          #",
                "#      #          #",
                "#      #          #",
                "###################"
        }));
        myRoboVac.setPosition(new Position(1, 1));

        myRoboVac.clean();

        System.out.printf("Cleaning completed in %d moves\n", myRoboVac.getMoveCount());
    }
}
