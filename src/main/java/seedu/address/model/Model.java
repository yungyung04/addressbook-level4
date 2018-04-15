package seedu.address.model;

import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.DuplicateTaskException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.person.exceptions.TimingClashException;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.exceptions.TaskNotFoundException;
import seedu.address.model.tutee.Tutee;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    Predicate<Task> PREDICATE_SHOW_ALL_TASKS = unused -> true;

    /** {@code Predicate} that evaluates to true if a parent object stores an instance of the subclass object*/
    Predicate<Person> PREDICATE_SHOW_ALL_TUTEES = person -> person instanceof Tutee;

    /** Clears existing backing model and replaces with the provided new data. */
    void resetData(ReadOnlyAddressBook newData);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /** Deletes the given person. */
    void deletePerson(Person target) throws PersonNotFoundException;

    /** Adds the given person */
    void addPerson(Person person) throws DuplicatePersonException;

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     *
     * @throws DuplicatePersonException if updating the person's details causes the person to be equivalent to
     *      another existing person in the list.
     * @throws PersonNotFoundException if {@code target} could not be found in the list.
     */
    void updatePerson(Person target, Person editedPerson)
            throws DuplicatePersonException, PersonNotFoundException;

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);


    void addTask (Task target) throws TimingClashException, DuplicateTaskException;

    void deleteTask(Task target) throws TaskNotFoundException;

    ObservableList<Task> getFilteredTaskList();

    void updateFilteredTaskList(Predicate<Task> predicate);

    /**
     * Removes the given {@code tag} from the specified {@code person}.
     */
    void deleteTag(Tag tag, Person person);

    /**
     * Sorts the list of person according to the given comparator
     */
    void sortFilteredPersonList (Comparator<Person> comparator);

    /**
     * Sorts the list of tasks according to the given comparator
     */
    void sortFilteredTaskList (Comparator<Task> comparator);
}
