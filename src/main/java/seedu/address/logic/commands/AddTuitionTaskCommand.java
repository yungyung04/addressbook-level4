package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;

import seedu.address.logic.commands.exceptions.CommandException;

import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicateTaskException;
import seedu.address.model.person.exceptions.TimingClashException;
import seedu.address.model.tutee.TuitionTask;
import seedu.address.model.tutee.Tutee;

//@@author yungyung04

/**
 * Adds a tuition (task) into the schedule.
 */
public class AddTuitionTaskCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "addtuition";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a tuition (task) into the schedule.\n"
            + "Parameters: "
            + "tutee_index"
            + "Date(dd/mm/yyyy) "
            + "Start time(hh:mm) "
            + "Duration(XXhXXm) "
            + "Description( anything; leading and trailing whitespaces will be trimmed )\n"
            + "Example: " + COMMAND_WORD + " "
            + "1 "
            + "10/12/2018 "
            + "12:30 "
            + "1h30m "
            + "Calculus homework page 24";

    public static final String MESSAGE_SUCCESS = "New tuition task added.";

    private final Index targetIndex;
    private final LocalDateTime taskdateTime;
    private final String duration;
    private final String description;

    private TuitionTask toAdd;
    private String associatedTutee;

    /**
     * Creates an AddTuition to add the specified {@code Task} which is associated to {@code Tutee}.
     */
    public AddTuitionTaskCommand(Index targetIndex, LocalDateTime taskDateTime, String duration, String description) {
        requireNonNull(taskDateTime);
        requireNonNull(duration);
        requireNonNull(description);
        this.targetIndex = targetIndex;
        this.taskdateTime = taskDateTime;
        this.duration = duration;
        this.description = description;
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
        return new CommandResult(MESSAGE_SUCCESS);
    }

    //@@author yungyung04
    @Override
    protected void preprocessUndoableCommand() throws CommandException {
        associatedTutee = getAssociatedTutee().getName().fullName;
        toAdd = new TuitionTask(associatedTutee, taskdateTime, duration, description);
    }

    /**
     * Returns the {@code Tutee} object that is pointed by the index as shown in the last displayed conatct list.
     */
    private Tutee getAssociatedTutee() throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        requireNonNull(lastShownList.get(targetIndex.getZeroBased()));
        Person associatedPerson = lastShownList.get(targetIndex.getZeroBased());
        if (!(associatedPerson instanceof Tutee)) {
            throw new CommandException(Messages.MESSAGE_INVALID_TUTEE_INDEX);
        }
        return (Tutee) lastShownList.get(targetIndex.getZeroBased());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddTuitionTaskCommand // instanceof handles nulls
                && targetIndex.equals(((AddTuitionTaskCommand) other).targetIndex))
                && taskdateTime.equals(((AddTuitionTaskCommand) other).taskdateTime)
                && duration.equals(((AddTuitionTaskCommand) other).duration)
                && description.equals(((AddTuitionTaskCommand) other).description);

    }
}
