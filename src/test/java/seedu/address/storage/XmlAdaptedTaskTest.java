package seedu.address.storage;

import static org.junit.Assert.assertEquals;
import static seedu.address.testutil.TaskUtil.toLocalDateAndTime;
import static seedu.address.testutil.TypicalTasks.EXAMPLE1;

import java.time.LocalDateTime;

import org.junit.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.testutil.Assert;

public class XmlAdaptedTaskTest {
    private static final String INVALID_DURATION = "3:20";
    private static final String INVALID_DATEANDTIME = "2018-05-02";

    private static final String VALID_DESCRIPTION = "A description";

    private static final String VALID_DURATION = EXAMPLE1.getDuration();
    private static final String VALID_DATEANDTIME = EXAMPLE1.getTaskDateTime().toString(); //Double check this

    @Test
    public void toModelType_validTaskDetails_returnsTask() throws Exception {
        XmlAdaptedTask task = new XmlAdaptedTask(EXAMPLE1);
        assertEquals(EXAMPLE1, task.toModelType());
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        XmlAdaptedTask task = new XmlAdaptedTask(null, VALID_DURATION, VALID_DATEANDTIME);
        String expectedMessage = "Description cannot be set to null";
        Assert.assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidDuration_throwsIllegalValueException() {
        XmlAdaptedTask task =
                new XmlAdaptedTask(VALID_DESCRIPTION, INVALID_DURATION, VALID_DATEANDTIME);
        String expectedMessage = "Invalid Duration";
        Assert.assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullDuration_throwsIllegalValueException() {
        XmlAdaptedTask task = new XmlAdaptedTask(VALID_DESCRIPTION, null, VALID_DATEANDTIME);
        String expectedMessage = "Duration cannot be set to null";
        Assert.assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidDateAndTime_throwsIllegalValueException() {
        XmlAdaptedTask task =
                new XmlAdaptedTask(VALID_DESCRIPTION, VALID_DURATION, INVALID_DATEANDTIME);
        String expectedMessage = "Invalid Duration";
        Assert.assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullDateAndTime_throwsIllegalValueException() {
        XmlAdaptedTask task = new XmlAdaptedTask(VALID_DESCRIPTION, VALID_DURATION, null);
        String expectedMessage = "Date and Time cannot be left blank";
        Assert.assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

}
