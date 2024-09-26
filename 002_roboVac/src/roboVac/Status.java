package roboVac;

public enum Status {
    CLEAN(' '),
    DIRTY('.'),
    WALL('#');

    public final char label;

    private Status(char label) {
        this.label = label;
    }

    public static Status valueOfLabel(char label) {
        for (var status : Status.values()) {
            if (status.label == label) {
                return status;
            }
        }

        return null;
    }
}
