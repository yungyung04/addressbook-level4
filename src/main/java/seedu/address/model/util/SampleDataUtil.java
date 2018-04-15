package seedu.address.model.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.Task;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.DuplicateTaskException;
import seedu.address.model.person.exceptions.TimingClashException;
import seedu.address.model.personal.PersonalTask;
import seedu.address.model.tag.Tag;
import seedu.address.model.tutee.TuitionTask;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static final String DATETIME1 = "03/04/2018 02:30";
    public static final String DATETIME2 = "05/04/2018 02:30";
    public static final String DATETIME3 = "07/04/2018 02:30";
    public static final String DATETIME4 = "03/04/2019 02:30";
    public static final String DATETIME5 = "05/04/2020 02:30";
    public static final String DATETIME6 = "15/04/2018 02:30";
    public static final String DATETIME7 = "20/04/2018 02:30";
    public static final String DATETIME8 = "22/04/2018 02:30";
    public static final String DATETIME9 = "23/04/2018 02:30";
    public static final String DATETIME10 = "13/04/2018 02:30";
    public static final String DATETIME11 = "29/04/2018 02:30";
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm")
            .withResolverStyle(ResolverStyle.STRICT);

    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"))
        };
    }
    public static Task[] getSampleTasks() {
        return new Task[]{
            new PersonalTask(LocalDateTime.parse(DATETIME1, formatter), "2h30m", "exampleTask1"),
            new TuitionTask("Alice", LocalDateTime.parse(DATETIME2, formatter), "2h30m", "exampleTask2"),
            new PersonalTask(LocalDateTime.parse(DATETIME3, formatter), "2h30m", "exampleTask3"),
            new PersonalTask(LocalDateTime.parse(DATETIME4, formatter), "2h30m", "exampleTask4"),
            new PersonalTask(LocalDateTime.parse(DATETIME5, formatter), "2h30m", "exampleTask5"),
            new PersonalTask(LocalDateTime.parse(DATETIME6, formatter), "2h30m", "exampleTask6"),
            new PersonalTask(LocalDateTime.parse(DATETIME7, formatter), "2h30m", "exampleTask7"),
            new PersonalTask(LocalDateTime.parse(DATETIME8, formatter), "2h30m", "exampleTask8"),
            new TuitionTask("John", LocalDateTime.parse(DATETIME9, formatter), "2h30m",
                    "exampleTask9"),
            new TuitionTask("Eka", LocalDateTime.parse(DATETIME10, formatter), "2h30m",
                    "exampleTask10"),
            new TuitionTask("Chochitun", LocalDateTime.parse(DATETIME11, formatter), "2h30m",
                    "exampleTask11"),
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        try {
            AddressBook sampleAb = new AddressBook();
            for (Person samplePerson : getSamplePersons()) {
                sampleAb.addPerson(samplePerson);
            }
            for (Task sampleTask : getSampleTasks()) {
                sampleAb.addTask(sampleTask);
            }
            return sampleAb;
        } catch (DuplicatePersonException e) {
            throw new AssertionError("sample data cannot contain duplicate persons", e);
        } catch (DuplicateTaskException dte) {
            throw new AssertionError("sample data cannot contain duplicate tasks", dte);
        } catch (TimingClashException tce) {
            throw new AssertionError("Timing clash detected", tce);
        }
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        HashSet<Tag> tags = new HashSet<>();
        for (String s : strings) {
            tags.add(new Tag(s));
        }

        return tags;
    }

}
