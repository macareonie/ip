package tsundere.task;

public class ToDo extends Task {

    protected static final String type = "T";

    /**
     * Initializes ToDo task with its name.
     *
     * @param description Name of ToDo.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns formatted String for storage purposes.
     *
     * @return formatted saveString.
     */
    @Override
    public String toSaveString() {
        return type + "," + super.toSaveString() + "," + this.getTagString();
    }

    /**
     * Returns String representation of ToDo.
     *
     * @return String representation of ToDo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

