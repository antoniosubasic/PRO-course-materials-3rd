package roboVac;

public class Room {
    private Status[][] layout;

    public Room(String[] layout) {
        this.layout = new Status[layout.length][layout[0].length()];

        for (var y = 0; y < this.layout.length; y++) {
            for (var x = 0; x < this.layout[y].length; x++) {
                this.layout[y][x] = Status.valueOfLabel(layout[y].charAt(x));
            }
        }
    }
}
