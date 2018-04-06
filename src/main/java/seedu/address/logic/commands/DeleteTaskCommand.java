package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;

import seedu.address.logic.commands.exceptions.CommandException;

import seedu.address.model.Task;
import seedu.address.model.task.exceptions.TaskNotFoundException;
import seedu.address.model.tutee.TuitionTask;

//@@author yungyung04
/**
 * Deletes a task from the schedule.
 */
public class DeleteTaskCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "deletetask";

    public static final String MESSAGE_USAGE =
            COMMAND_WORD + ": Deletes a tuition or personal task from the schedule.\n"
            + "Parameters: "
            + "index of Task"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Deleted task : %1$s";

    private final Index targetIndex;
    private Task toDelete;

    public DeleteTaskCommand(Index indexOfTask) {
        targetIndex = indexOfTask;
    }

    @Override
    public CommandResult executeUndoableCommand() {
        requireNonNull(toDelete);
        try {
            model.deleteTask(toDelete);
        } catch (TaskNotFoundException tnfe) {
            throw new AssertionError("The target person cannot be missing");
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, toDelete.toString()));
    }

    @Override
    protected void preprocessUndoableCommand() throws CommandException {
        toDelete = getAssociatedTask();
        if (toDelete instanceof TuitionTask) {
            //throw exceptions if tuition is not marked as resolved yet. Why? if it is unresolved, the fee is not
            //recorded as bill yet.
        }

    }

    private Task getAssociatedTask() throws CommandException {
        List<Task> lastShownTaskList = model.getFilteredTaskList();

        if (targetIndex.getZeroBased() >= lastShownTaskList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        return lastShownTaskList.get(targetIndex.getZeroBased());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteTaskCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteTaskCommand) other).targetIndex));
    }
}
