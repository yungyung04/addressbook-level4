package seedu.address.logic.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static seedu.address.logic.commands.CommandTestUtil.deleteFirstPerson;
import static seedu.address.logic.commands.CommandTestUtil.deleteFirstTask;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.logic.commands.CommandTestUtil.showTaskAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTasks.getTypicalAddressBook;

import org.junit.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.Task;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.task.exceptions.TaskNotFoundException;
import seedu.address.testutil.TypicalPersons;
import seedu.address.testutil.TypicalTasks;

public class UndoableCommandTest {
    private final Model model = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs());
    private final Model modelT = new ModelManager(TypicalTasks.getTypicalAddressBook(), new UserPrefs());

    private final DummyCommand dummyCommand = new DummyCommand(model);
    private final DummyCommand dummyCommandT = new DummyCommand(modelT);

    private Model expectedModel = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs());
    private Model expectedModelT = new ModelManager(TypicalTasks.getTypicalAddressBook(), new UserPrefs());

    @Test
    public void executeUndo() throws Exception {
        dummyCommand.execute();
        deleteFirstPerson(expectedModel);
        assertEquals(expectedModel, model);

        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        // undo() should cause the model's filtered list to show all persons
        dummyCommand.undo();
        expectedModel = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs());
        assertEquals(expectedModel, model);
    }

    @Test
    public void executeUndoT() throws Exception {
        dummyCommandT.execute();
        deleteFirstTask(expectedModelT);
        assertEquals(expectedModelT, modelT);

        showTaskAtIndex(modelT, INDEX_FIRST_PERSON);
        // undo() should cause the model's filtered list to show all tasks
        dummyCommandT.undo();
        expectedModelT = new ModelManager(TypicalTasks.getTypicalAddressBook(), new UserPrefs());
        assertEquals(expectedModelT, modelT);
    }

    @Test
    public void redo() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        showTaskAtIndex(model, INDEX_FIRST_PERSON);
        // redo() should cause the model's filtered list to show all persons
        dummyCommand.redo();
        deleteFirstPerson(expectedModel);
        assertEquals(expectedModel, model);
    }

    @Test
    public void redoT() {
        showTaskAtIndex(modelT, INDEX_FIRST_PERSON);
        // redo() should cause the model's filtered list to show all tasks
        dummyCommandT.redo();
        deleteFirstTask(expectedModelT);
        assertEquals(expectedModelT, modelT);
    }

    /**
     * Deletes the first person in the model's filtered list.
     */
    class DummyCommand extends UndoableCommand {
        DummyCommand(Model model) {
            this.model = model;
        }

        @Override
        public CommandResult executeUndoableCommand() throws CommandException {
            Person personToDelete = model.getFilteredPersonList().get(0);
            Task taskToDelete = model.getFilteredTaskList().get(0);
            if (personToDelete == null) {
                try {
                    model.deleteTask(taskToDelete);
                } catch (TaskNotFoundException pnfe) {
                    fail("Impossible: taskToDelete was retrieved from model.");
                }
            } else {
                try {
                    model.deletePerson(personToDelete);
                } catch (PersonNotFoundException pnfe) {
                    fail("Impossible: personToDelete was retrieved from model.");
                }
            }
            return new CommandResult("");
        }
    }
}
