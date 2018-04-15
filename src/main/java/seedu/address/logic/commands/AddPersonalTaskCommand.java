package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.exceptions.DuplicateTaskException;
import seedu.address.model.person.exceptions.TimingClashException;
import seedu.address.model.personal.PersonalTask;

//@@author yungyung04
/**
 * Adds a personal task into the schedule.
 */
public class AddPersonalTaskCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "addtask";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a personal task into the schedule.\n"
            + "Parameters: "
            + "Date(dd/mm/yyyy) "
            + "Start time(hh:mm) "
            + "Duration(XXhXXm) "
            + "Description( anything; leading and trailing whitespaces will be trimmed )\n"
            + "Example: " + COMMAND_WORD + " "
            + "10/12/2018 "
            + "12:30 "
            + "1h30m "
            + "Yoga";
    public static final String MESSAGE_SUCCESS = "Task added: %1$s";

    private final PersonalTask toAdd;

    /**
     * Creates an AddPersonalTaskCommand to add the specified {@code Task}.
     */
    public AddPersonalTaskCommand(PersonalTask task) {
        requireNonNull(task);
        toAdd = task;
    }

    //@@author ChoChihTun
    @Override
    public CommandResult executeUndoableCommand() throws CommandException {
        try {
            model.addTask(toAdd);
        } catch (TimingClashException tce) {
            throw new CommandException(tce.getMessage());
        } catch (DuplicateTaskException dte) {
            throw new CommandException(dte.getMessage());
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    //@@author yungyung04
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddPersonalTaskCommand // instanceof handles nulls
                && toAdd.equals(((AddPersonalTaskCommand) other).toAdd));
    }
}
