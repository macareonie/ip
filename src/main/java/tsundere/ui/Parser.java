package tsundere.ui;

import tsundere.exception.GeneralException;
import tsundere.task.TaskList;

public class Parser {

    public static String name;
    public Parser(String name) {
        Parser.name = name;
    }

    /**
     * Parses command given and executes corresponding action.
     *
     * @throws GeneralException If actions executed cause exceptions.
     */
    public void execute() throws GeneralException {

        if (name.contains("unmark")) {
            TaskList.unmark();
        } else if (name.contains("mark")) {
            TaskList.mark();
        } else if (name.contains("delete")) {
            TaskList.delete();
        } else if (name.contains("list")) {
            TaskList.list();
        } else if (name.contains("deadline")) {
            TaskList.addDeadline();
        } else if (name.contains("event")) {
            TaskList.addEvent();
        } else if (name.contains("todo")) {
            TaskList.addToDo();
        } else if (name.contains("find")) {
            TaskList.find();
        } else {
            System.out.println("Don't talk to me!\nGive me proper instructions!");
        }

    }
}
