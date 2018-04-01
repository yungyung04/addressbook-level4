package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import static org.junit.Assert.fail;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_TIME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DURATION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_DESC;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Predicate;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javafx.collections.ObservableList;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.Schedule;
import seedu.address.model.Task;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.person.exceptions.TimingClashException;
import seedu.address.model.personal.PersonalSchedule;
import seedu.address.model.personal.PersonalTask;
import seedu.address.model.tag.Tag;


public class AddPersonalTaskCommandTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();


    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm")
            .withResolverStyle(ResolverStyle.STRICT);
    private LocalDateTime taskDateTime = LocalDateTime.parse(VALID_DATE_TIME, formatter);
    private PersonalTask task = new PersonalTask(taskDateTime, VALID_DURATION, VALID_TASK_DESC);

    @Test
    public void constructor_nullTask_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new AddPersonalTaskCommand(null);
    }

    @Test
    public void execute_validPersonalTask_addSuccessful() {
        ArrayList<Task> taskListCopy = Schedule.getTaskList();
        ArrayList<PersonalTask> personalTaskListCopy = PersonalSchedule.getPersonalTaskList();
        PersonalTask task = new PersonalTask(taskDateTime, VALID_DURATION, VALID_TASK_DESC);

        AddPersonalTaskCommand command = new AddPersonalTaskCommand(task);
        CommandResult commandResult = command.executeUndoableCommand();

        assertEquals(AddPersonalTaskCommand.MESSAGE_SUCCESS, commandResult.feedbackToUser);

        //taskList should be updated
        taskListCopy.add(task);
        assertEquals(taskListCopy, Schedule.getTaskList());

        //personalTaskList should be updated
        personalTaskListCopy.add(task);
        assertEquals(personalTaskListCopy, PersonalSchedule.getPersonalTaskList());
    }

    @Test
    public void equals() {
        LocalDateTime taskDateTime2 = LocalDateTime.parse("08/08/1988 18:00", formatter);
        PersonalTask task2 = new PersonalTask(taskDateTime2, VALID_DURATION, VALID_TASK_DESC);

        AddPersonalTaskCommand addFirstTask = new AddPersonalTaskCommand(task);
        AddPersonalTaskCommand addFirstTaskCopy = new AddPersonalTaskCommand(task);
        AddPersonalTaskCommand addSecondTask = new AddPersonalTaskCommand(task2);

        // same object -> returns true
        assertTrue(addFirstTask.equals(addFirstTask));

        // same values -> returns true
        assertTrue(addFirstTask.equals(addFirstTaskCopy));

        // different types -> returns false
        assertFalse(addFirstTask.equals(1));

        // null -> returns false
        assertFalse(addFirstTask.equals(null));

        // different person -> returns false
        assertFalse(addFirstTask.equals(addSecondTask));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {

        @Override
        public void addPerson(Person person) throws DuplicatePersonException {
            fail("This method should not be called.");
        }

        @Override
        public void addTask(Task task) throws TimingClashException {
            fail("This method should not be called");
        }

        @Override
        public void deleteTask(Task task) {
            fail("This method should not be called");
        }

        @Override
        public void updateTask(Task task, Task editedTask) {
            fail("This method should not be called");
        }

        @Override
        public ObservableList<Task> getFilteredTaskList() {
            fail("This method should not be called.");
            return null;
        }

        @Override
        public void updateFilteredTaskList(Predicate<Task> predicate) {
            fail("This method should not be called.");
        }

        @Override
        public void resetData(ReadOnlyAddressBook newData) {
            fail("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            fail("This method should not be called.");
            return null;
        }

        @Override
        public void deletePerson(Person target) throws PersonNotFoundException {
            fail("This method should not be called.");
        }

        @Override
        public void updatePerson(Person target, Person editedPerson)
                throws DuplicatePersonException {
            fail("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            fail("This method should not be called.");
            return null;
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            fail("This method should not be called.");
        }

        @Override
        public void deleteTag(Tag tag, Person person) {
            fail("deleteTag should not be called when adding Person.");
        }

        @Override
        public void sortFilteredPersonList(Comparator<Person> comparator) {
            fail("This method should not be called.");
        }
    }

    /**
     * A Model stub that always throw a DuplicatePersonException when trying to add a person.
     */
    private class ModelStubThrowingDuplicatePersonException extends AddPersonalTaskCommandTest.ModelStub {

        @Override
        public void addTask(Task task) throws TimingClashException {
            throw new TimingClashException();
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingPersonAdded extends AddPersonalTaskCommandTest.ModelStub {
        final ArrayList<Task> tasksAdded = new ArrayList<>();

        @Override
        public void addTask(Task person) throws TimingClashException {
            requireNonNull(person);
            tasksAdded.add(person);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }
}
