package seedu.address.testutil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

import seedu.address.model.Task;
import seedu.address.model.personal.PersonalTask;
import seedu.address.model.tutee.TuitionTask;

/**
 * A utility class to help with building Person objects.
 */
public class TaskBuilder {

    public static final String DEFAULT_NAME = "Someone";
    public static final String DEFAULT_DESCRIPTION = "This is an example task";
    public static final String DEFAULT_DURATION = "3h20m";
    public static final String DEFAULT_DATEANDTIME = "02/05/2018 23:20";

    protected String name;
    protected String description;
    protected String duration;
    protected LocalDateTime dateAndTime;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm")
            .withResolverStyle(ResolverStyle.STRICT);




    public TaskBuilder() {
        name = null;
        description = DEFAULT_DESCRIPTION;
        duration = DEFAULT_DURATION;
        dateAndTime = LocalDateTime.parse(DEFAULT_DATEANDTIME, formatter);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        name = taskToCopy.getEntry().getTitle();
        description = taskToCopy.getDescription();
        duration = taskToCopy.getDuration();
        dateAndTime = taskToCopy.getTaskDateTime();
    }

    /**
     * Sets the {@code name} of the {@code Task} that we are building.
     */
    public TaskBuilder withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Sets the {@code description} of the {@code Task} that we are building.
     */
    public TaskBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Sets the {@code duration} of the {@code Task} that we are building.
     */
    public TaskBuilder withDuration(String duration) {
        this.duration = duration;
        return this;
    }

    /**
     * Sets the {@code DateAndTime} of the {@code Task} that we are building.
     */

    public TaskBuilder withDateAndTime(String dateAndTime) {

        this.dateAndTime = LocalDateTime.parse(dateAndTime, formatter);
        return this;
    }
    /**
     * Ideally, this return variable should be made to a Task class or this function should return
     * a tuition task as well
     */

    public Task build() {
        if (name == null) {
            return new PersonalTask(dateAndTime, duration, description);
        } else {
            return new TuitionTask(name, dateAndTime, duration, description);
        }
    }
}
