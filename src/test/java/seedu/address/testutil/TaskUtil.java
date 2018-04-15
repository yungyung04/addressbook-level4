package seedu.address.testutil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

import seedu.address.logic.commands.AddPersonalTaskCommand;
import seedu.address.model.Task;


/**
 * A utility class for Task.
 */
//@@author a-shakra
public class TaskUtil {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm")
            .withResolverStyle(ResolverStyle.STRICT);

    /**
     * Returns an add personal task command string for adding the {@code task}.
     */
    public static String getAddPersonalTaskCommand(Task task) {
        return AddPersonalTaskCommand.COMMAND_WORD + " " + getPersonalTaskDetails(task);
    }

    /**
     * Returns the part of command string for the given {@code task}'s details.
     */
    public static String getPersonalTaskDetails(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append(task.getStringTaskDateTime() + " ");
        sb.append(task.getDuration() + " ");
        sb.append(task.getDescription() + " ");
        return sb.toString();
    }

    /**
     * Converts a date and time string to a local date time object
     */
    public static LocalDateTime toLocalDateAndTime(String sDateAndTime) {
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-uuuu HH:mm");
        LocalDateTime lDateAndTime = LocalDateTime.parse(sDateAndTime, FORMATTER);
        return lDateAndTime;
    }
}
