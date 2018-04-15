package seedu.address.ui.testutil;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.stream.Collectors;

import guitests.guihandles.PersonCardHandle;
import guitests.guihandles.PersonListPanelHandle;
import guitests.guihandles.ResultDisplayHandle;
import guitests.guihandles.TaskCardHandle;
import guitests.guihandles.TaskListPanelHandle;
import seedu.address.model.Task;
import seedu.address.model.person.Person;

/**
 * A set of assertion methods useful for writing GUI tests.
 */
public class GuiTestAssert {
    /**
     * Asserts that {@code actualCard} displays the same values as {@code expectedCard}.
     */
    public static void assertCardEquals(PersonCardHandle expectedCard, PersonCardHandle actualCard) {
        assertEquals(expectedCard.getId(), actualCard.getId());
        assertEquals(expectedCard.getAddress(), actualCard.getAddress());
        assertEquals(expectedCard.getEmail(), actualCard.getEmail());
        assertEquals(expectedCard.getName(), actualCard.getName());
        assertEquals(expectedCard.getPhone(), actualCard.getPhone());
        assertEquals(expectedCard.getTags(), actualCard.getTags());
    }

    /**
     * Asserts that {@code actualCard} displays the same values as {@code expectedCard}.
     */
    public static void assertCardEquals(TaskCardHandle expectedCard, TaskCardHandle actualCard) {
        assertEquals(expectedCard.getDescription(), actualCard.getDescription());
        assertEquals(expectedCard.getDuration(), actualCard.getDuration());
        assertEquals(expectedCard.getDateAndTime(), actualCard.getDateAndTime());
    }

    /**
     * Asserts that {@code actualCard} displays the details of {@code expectedPerson}.
     */
    public static void assertCardDisplaysPerson(Person expectedPerson, PersonCardHandle actualCard) {
        assertEquals(expectedPerson.getName().fullName, actualCard.getName());
        assertEquals(expectedPerson.getPhone().value, actualCard.getPhone());
        assertEquals(expectedPerson.getEmail().value, actualCard.getEmail());
        assertEquals(expectedPerson.getAddress().value, actualCard.getAddress());
        assertEquals(expectedPerson.getTags().stream().map(tag -> tag.tagName).collect(Collectors.toList()),
                actualCard.getTags());
    }

    /**
     * Asserts that {@code actualCard} displays the details of {@code expectedPerson}.
     */
    public static void assertCardDisplaysTask(Task expectedTask, TaskCardHandle actualCard) {
        assertEquals(expectedTask.getDescription(), actualCard.getDescription());
        assertEquals(expectedTask.getDuration(), actualCard.getDuration());
        assertEquals(expectedTask.getTaskDateTime().toString(), actualCard.getDateAndTime().toString());
    }


    /**
     * Asserts that the list in {@code personListPanelHandle} displays the details of {@code persons} correctly and
     * in the correct order.
     */
    public static void assertListMatching(PersonListPanelHandle personListPanelHandle, Person... persons) {
        for (int i = 0; i < persons.length; i++) {
            assertCardDisplaysPerson(persons[i], personListPanelHandle.getPersonCardHandle(i));
        }
    }

    /**
     * Asserts that the list in {@code taskListPanelHandle} displays the details of {@code tasks} correctly and
     * in the correct order.
     */
    public static void assertListMatching(TaskListPanelHandle taskListPanelHandle, Task... tasks) {
        for (int i = 0; i < tasks.length; i++) {
            assertCardDisplaysTask(tasks[i], taskListPanelHandle.getTaskCardHandle(i));
        }
    }

    /**
     * Asserts that the list in {@code personListPanelHandle} displays the details of {@code persons} correctly and
     * in the correct order.
     */
    public static void assertListMatching(PersonListPanelHandle personListPanelHandle, List<Person> persons) {
        assertListMatching(personListPanelHandle, persons.toArray(new Person[0]));
    }

    /**
     * Asserts that the list in {@code taskListPanelHandle} displays the details of {@code tasks} correctly and
     * in the correct order.
     */
    public static void assertListMatching(TaskListPanelHandle taskListPanelHandle, List<Task> tasks) {
        assertListMatching(taskListPanelHandle, tasks.toArray(new Task[0]));
    }

    /**
     * Asserts the size of the list in {@code personListPanelHandle} equals to {@code size}.
     */
    public static void assertListSize(PersonListPanelHandle personListPanelHandle, int size) {
        int numberOfPeople = personListPanelHandle.getListSize();
        assertEquals(size, numberOfPeople);
    }

    /**
     * Asserts the size of the list in {@code personListPanelHandle} equals to {@code size}.
     */
    public static void assertListSize(TaskListPanelHandle taskListPanelHandle, int size) {
        int numberOfTasks = taskListPanelHandle.getListSize();
        assertEquals(size, numberOfTasks);
    }

    /**
     * Asserts the message shown in {@code resultDisplayHandle} equals to {@code expected}.
     */
    public static void assertResultMessage(ResultDisplayHandle resultDisplayHandle, String expected) {
        assertEquals(expected, resultDisplayHandle.getText());
    }
}

