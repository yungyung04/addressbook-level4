package seedu.address.testutil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

import seedu.address.logic.commands.AddPersonalTaskCommand;

import seedu.address.model.Task;

/**
 * A utility class for Task. For now, this is customized to the personal task class
 */
public class TaskUtil {

    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm")
            .withResolverStyle(ResolverStyle.STRICT);

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddTaskCommand(Task task) {
        return AddPersonalTaskCommand.COMMAND_WORD + " " + getTaskDetails(task);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getTaskDetails(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append(task.getTaskDateTime() + " ");
        sb.append(task.getDuration() + " ");
        sb.append(task.getDescription() + " ");
        return sb.toString();
    }

    /**
     * Converts a date and time string to a local date time object
     */
    public static LocalDateTime toLocalDateAndTime(String sDateAndTime) {
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-uuuu HH:mm");
        LocalDateTime lDateAndTime = LocalDateTime.parse(sDateAndTime, formatter);
        return lDateAndTime;
    }
}
