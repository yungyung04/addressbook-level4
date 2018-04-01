package seedu.address.testutil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import seedu.address.model.Task;
import seedu.address.model.personal.PersonalTask;

/**
 * A utility class to help with building Person objects.
 */
public class TaskBuilder {

    public static final String DEFAULT_DESCRIPTION = "This is an example task";
    public static final String DEFAULT_DURATION = "alice@gmail.com";
    public static final String DEFAULT_DATEANDTIME = "02-05-2018 23:20";

    protected String description;
    protected String duration;
    protected LocalDateTime dateAndTime;

    public TaskBuilder() {
        description = DEFAULT_DESCRIPTION;
        duration = DEFAULT_DURATION;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-uuuu HH:mm");
        dateAndTime = LocalDateTime.parse(DEFAULT_DATEANDTIME, formatter);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        description = taskToCopy.getDescription();
        duration = taskToCopy.getDuration();
        dateAndTime = taskToCopy.getTaskDateTime();
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-uuuu HH:mm");
        this.dateAndTime = LocalDateTime.parse(dateAndTime, formatter);
        return this;
    }
    /**
     * Ideally, this return variable should be made to a Task class or this function should return
     * a tuition task as well
     */

    public Task build() {
        return new PersonalTask(dateAndTime, duration, description);
    }

}
