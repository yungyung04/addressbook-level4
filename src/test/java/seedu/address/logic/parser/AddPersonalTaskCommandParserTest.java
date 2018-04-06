package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DATE_TIME;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DURATION;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.Test;

import seedu.address.logic.commands.AddPersonalTaskCommand;


public class AddPersonalTaskCommandParserTest {
    private AddPersonalTaskCommandParser parser = new AddPersonalTaskCommandParser();

    //@@author ChoChihTun

    //private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm")
    //        .withResolverStyle(ResolverStyle.STRICT);


    @Test
    public void parse_invalidArgs_throwsParseException() {
        // Invalid format
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPersonalTaskCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "1 11/01/2018 11:11 1h30m Outing",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPersonalTaskCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "11:11 1h30m Outing with friends",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPersonalTaskCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "11/01/2018 1h30m Outing with friends",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPersonalTaskCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "11/01/2018 11:11 Outing with friends",
                 String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPersonalTaskCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "11/01/2018 11:11 1h Outing with friends",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPersonalTaskCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "11/01/2018 11:11 30m Outing with friends",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPersonalTaskCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "11:11 32/01/2018 1h30m Outing with friends",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPersonalTaskCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "aa/01/2018 11:11 1h30m Outing with friends",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPersonalTaskCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "32/01/2018 11:aa 1h30m Outing with friends",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPersonalTaskCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "32/01/2018 11:11 1haam Outing with friends",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPersonalTaskCommand.MESSAGE_USAGE));

        // Invalid date
        assertParseFailure(parser, "32/01/2018 11:11 1h30m Outing with friends",
                MESSAGE_INVALID_DATE_TIME + "\n" + AddPersonalTaskCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "29/02/2018 11:11 1h30m Outing with friends",
                MESSAGE_INVALID_DATE_TIME + "\n" + AddPersonalTaskCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "31/04/2018 11:11 1h30m Outing with friends",
                MESSAGE_INVALID_DATE_TIME + "\n" + AddPersonalTaskCommand.MESSAGE_USAGE);

        // Invalid time
        assertParseFailure(parser, "11/01/2018 31:11 1h30m Outing with friends",
                MESSAGE_INVALID_DATE_TIME + "\n" + AddPersonalTaskCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "11/01/2018 11:60 1h30m Outing with friends",
                MESSAGE_INVALID_DATE_TIME + "\n" + AddPersonalTaskCommand.MESSAGE_USAGE);

        // Invalid duration
        assertParseFailure(parser, "11/01/2018 11:11 1h60m Outing with friends",
                MESSAGE_INVALID_DURATION + "\n" + AddPersonalTaskCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "11/01/2018 11:11 24h0m Outing with friends",
                MESSAGE_INVALID_DURATION + "\n" + AddPersonalTaskCommand.MESSAGE_USAGE);
    }

    /**
    @Test
    public void parse_validArgs_success() {
        // With description
        LocalDateTime taskDateTime = LocalDateTime.parse(VALID_DATE_TIME, formatter);
        PersonalTask personalTask = new PersonalTask(taskDateTime, VALID_DURATION, VALID_TASK_DESC);
        assertParseSuccess(parser, VALID_TASK_WITH_DESC,
                new AddPersonalTaskCommand(personalTask));

        // Without description
        personalTask = new PersonalTask(taskDateTime, VALID_DURATION, VALID_EMPTY_TASK_DESC);
        assertParseSuccess(parser, VALID_TASK_WITHOUT_DESC,
                new AddPersonalTaskCommand(personalTask));

        // Check leap year
        personalTask = new PersonalTask(LocalDateTime.parse("29/02/2016 11:20", formatter),
                VALID_DURATION, VALID_EMPTY_TASK_DESC);
        assertParseSuccess(parser, "29/02/2016 11:20 1h11m",
                new AddPersonalTaskCommand(personalTask));
    }
    */

}
