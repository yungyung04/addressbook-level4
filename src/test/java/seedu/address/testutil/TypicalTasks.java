package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.Task;
import seedu.address.model.person.exceptions.DuplicateTaskException;

/**
 * A utility class for Task. For now, this is customized to the personal task class
 */
public class TypicalTasks {

    public static final Task EXAMPLE1 = new TaskBuilder().withDescription("exampleTask1").withDuration("03:20")
            .withDateAndTime("02-05-2018 03:20").build();
    public static final Task EXAMPLE2 = new TaskBuilder().withDescription("exampleTask2").withDuration("03:20")
            .withDateAndTime("02-05-2018 13:20").build();
    public static final Task EXAMPLE3 = new TaskBuilder().withDescription("exampleTask3").withDuration("03:20")
            .withDateAndTime("02-05-2018 23:20").build();

    private TypicalTasks() {} // prevents instantiation
    /**
     * Returns an {@code AddressBook} with all the typical tasks.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Task task : getTypicalTasks()) {
            try {
                ab.addTask(task);
            } catch (DuplicateTaskException e) {
                throw new AssertionError("not possible");
            }
        }
        return ab;
    }

    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(EXAMPLE1, EXAMPLE2, EXAMPLE3));
    }

}
