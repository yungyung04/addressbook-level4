package seedu.address.model.personal;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

import com.calendarfx.model.Entry;
import com.calendarfx.model.Interval;

import seedu.address.model.Task;

//@@author ChoChihTun
/**
 * Represents the personal task that the user has
 */
public class PersonalTask implements Task {

    private static final String HOUR_DELIMITER = "h";
    private static final String MINUTE_DELIMITER = "m";
    private static final String NULL_STRING = "";

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm")
            .withResolverStyle(ResolverStyle.STRICT);
    private String description;
    private String duration;
    private LocalDateTime taskDateTime;
    private Entry entry;

    /**
     * Creates a personal task
     *
     * @param taskDateTime date and time of the task
     * @param duration duration of the task
     * @param description description of the task
     */
    public PersonalTask(LocalDateTime taskDateTime, String duration, String description) {
        this.taskDateTime = taskDateTime;
        this.duration = duration;
        this.description = description;
        this.entry = createCalendarEntry();
    }

    /**
     * Creates an entry to be entered into the calendar
     *
     * @return Calendar entry
     */
    private Entry createCalendarEntry() {
        LocalDateTime endDateTime = getTaskEndTime();
        Interval interval = new Interval(taskDateTime, endDateTime);
        Entry entry = new Entry(description);
        entry.setInterval(interval);
        return entry;
    }

    /**
     * Returns the end time of the task
     */
    private LocalDateTime getTaskEndTime() {
        int hoursInDuration = parseHours();
        int minutesInDuration = parseMinutes();
        LocalDateTime endDateTime = taskDateTime.plusHours(hoursInDuration).plusMinutes(minutesInDuration);
        return endDateTime;
    }

    /**
     * Parses hour component out of duration
     *
     * @return number of hours in the duration
     */
    private int parseHours() {
        int indexOfHourDelimiter = duration.indexOf(HOUR_DELIMITER);
        return Integer.parseInt(duration.substring(0, indexOfHourDelimiter));
    }

    /**
     * Parses minute component out of duration
     *
     * @return number of minutes in the duration
     */
    private int parseMinutes() {
        int startOfMinutesIndex = duration.indexOf(HOUR_DELIMITER) + 1;
        int indexOfMinuteDelimiter = duration.indexOf(MINUTE_DELIMITER);
        return Integer.parseInt(duration.substring(startOfMinutesIndex, indexOfMinuteDelimiter));
    }

    public Entry getEntry() {
        return entry;
    }

    public LocalDateTime getTaskDateTime() {
        return taskDateTime;
    }

    public String getDescription() {
        return description;
    }

    public String getDuration() {
        return duration;
    }

    @Override
    public String getStringTaskDateTime() {
        return taskDateTime.format(formatter);
    }

    //@@author yungyung04
    @Override
    public String toString() {
        if (hasDescription()) {
            return "Personal task with description " + description + " on "
                    + Integer.toString(taskDateTime.getDayOfMonth()) + " "
                    + taskDateTime.getMonth().name() + " " + Integer.toString(taskDateTime.getYear());
        } else {
            return "Personal task without description on " + Integer.toString(taskDateTime.getDayOfMonth())
                    + " " + taskDateTime.getMonth().name() + " " + Integer.toString(taskDateTime.getYear());
        }
    }

    /**
     * Returns true if the two tasks are equal. Needs to be updated to reflect the name parameter
     */


    /**
     * Returns true if the tuition task contains a non-empty description.
     */
    private boolean hasDescription() {
        return !description.equals(NULL_STRING);
    }


    /**
     * this fixes the valid args test, but has conflict with Task card
     * ^ I, a-shakra, didn't write this but I kept it because I wasn't sure what is meant by it
     * Remove if all is clear but notify me before please
     */
    //@@author a-shakra
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Task)) {
            return false;
        }

        Task otherTask = (Task) other;
        return otherTask.getDescription().equals(this.getDescription())
                && otherTask.getDuration().equals(this.getDuration())
                && otherTask.getTaskDateTime().toString().equals(this.getTaskDateTime().toString());
    }

}
