package seedu.address.logic.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.model.person.PersonSortUtil.CATEGORY_EDUCATION_LEVEL;
import static seedu.address.model.person.PersonSortUtil.CATEGORY_GRADE;
import static seedu.address.model.person.PersonSortUtil.CATEGORY_NAME;
import static seedu.address.model.person.PersonSortUtil.CATEGORY_SCHOOL;
import static seedu.address.model.person.PersonSortUtil.CATEGORY_SUBJECT;
import static seedu.address.testutil.typicaladdressbook.TypicalAddressBookCompiler.getTypicalAddressBook2;
import static seedu.address.testutil.typicaladdressbook.TypicalPersons.DANIEL;
import static seedu.address.testutil.typicaladdressbook.TypicalTutees.ALICETUTEE;
import static seedu.address.testutil.typicaladdressbook.TypicalTutees.AMYTUTEE;
import static seedu.address.testutil.typicaladdressbook.TypicalTutees.BOBTUTEE;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.UndoRedoStack;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

//@@author yungyung04
/**
 * Contains integration tests (interaction with the Model) for {@code SortPersonCommand}.
 */
public class SortPersonCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook2(), new UserPrefs());

    private final SortPersonCommand sortName = new SortPersonCommand(CATEGORY_NAME);

    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(sortName.equals(sortName));

        // same values -> returns true
        SortPersonCommand sortNameCopy = new SortPersonCommand(CATEGORY_NAME);
        assertTrue(sortName.equals(sortNameCopy));

        // different types -> returns false
        assertFalse(sortName.equals(1));

        // null -> returns false
        assertFalse(sortName.equals(null));

        // different category -> returns false
        SortPersonCommand sortGrade = new SortPersonCommand(CATEGORY_GRADE);
        assertFalse(sortName.equals(sortGrade));
    }

    @Test
    public void execute_sortName_sortedSuccessfully() {
        sortName.setData(model, new CommandHistory(), new UndoRedoStack());
        String expectedMessage = String.format(SortPersonCommand.MESSAGE_SUCCESS);
        assertCommandSuccess(sortName, expectedMessage,
                Arrays.asList(ALICETUTEE, AMYTUTEE, BOBTUTEE, DANIEL));
    }

    @Test
    public void execute_sortEducatonLevel_sortedSuccessfully() {
        SortPersonCommand sortEducationLevel = new SortPersonCommand(CATEGORY_EDUCATION_LEVEL);
        sortEducationLevel.setData(model, new CommandHistory(), new UndoRedoStack());
        String expectedMessage = String.format(SortPersonCommand.MESSAGE_SUCCESS);
        assertCommandSuccess(sortEducationLevel, expectedMessage,
                Arrays.asList(BOBTUTEE, ALICETUTEE, AMYTUTEE, DANIEL));
    }

    @Test
    public void execute_sortGrade_sortedSuccessfully() {
        SortPersonCommand sortGrade = new SortPersonCommand(CATEGORY_GRADE);
        sortGrade.setData(model, new CommandHistory(), new UndoRedoStack());
        String expectedMessage = String.format(SortPersonCommand.MESSAGE_SUCCESS);
        assertCommandSuccess(sortGrade, expectedMessage,
                Arrays.asList(BOBTUTEE, AMYTUTEE, ALICETUTEE, DANIEL));
    }

    @Test
    public void execute_sortSchool_sortedSuccessfully() {
        SortPersonCommand sortSchool = new SortPersonCommand(CATEGORY_SCHOOL);
        sortSchool.setData(model, new CommandHistory(), new UndoRedoStack());
        String expectedMessage = String.format(SortPersonCommand.MESSAGE_SUCCESS);
        assertCommandSuccess(sortSchool, expectedMessage,
                Arrays.asList(ALICETUTEE, AMYTUTEE, BOBTUTEE, DANIEL));
    }

    @Test
    public void execute_sortSubject_sortedSuccessfully() {
        SortPersonCommand sortSubject = new SortPersonCommand(CATEGORY_SUBJECT);
        sortSubject.setData(model, new CommandHistory(), new UndoRedoStack());
        String expectedMessage = String.format(SortPersonCommand.MESSAGE_SUCCESS);
        assertCommandSuccess(sortSubject, expectedMessage,
                Arrays.asList(AMYTUTEE, ALICETUTEE, BOBTUTEE, DANIEL));
    }

    /**
     * Asserts that {@code command} is successfully executed, and<br>
     *     - the command feedback is equal to {@code expectedMessage}<br>
     *     - the {@code FilteredList<Person>} is equal to {@code expectedList}<br>
     *     - the {@code AddressBook} in model remains the same after executing the {@code command}
     */
    private void assertCommandSuccess(SortPersonCommand command, String expectedMessage, List<Person> expectedList) {
        AddressBook expectedAddressBook = new AddressBook(model.getAddressBook());
        CommandResult commandResult = command.execute();

        assertEquals(expectedMessage, commandResult.feedbackToUser);
        assertEquals(expectedList, model.getFilteredPersonList());
        assertEquals(expectedAddressBook, model.getAddressBook());
    }
}
