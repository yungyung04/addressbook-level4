package seedu.address.logic.commands;

import static seedu.address.model.person.PersonSortUtil.CATEGORY_EDUCATION_LEVEL;
import static seedu.address.model.person.PersonSortUtil.CATEGORY_GRADE;
import static seedu.address.model.person.PersonSortUtil.CATEGORY_NAME;
import static seedu.address.model.person.PersonSortUtil.CATEGORY_SCHOOL;
import static seedu.address.model.person.PersonSortUtil.CATEGORY_SUBJECT;

import java.util.Comparator;

import seedu.address.model.person.Person;
import seedu.address.model.person.PersonSortUtil;

/**
 * Sorts all persons from the last shown list lexicographically according to the specified sorting category.
 * Since tutee contains specific information such as grade,
 * a Person who is not a tutee will be listed last when such information is selected to be the sorting category.
 */
public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_SUCCESS = "sorted successfully";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": sorts all visible persons lexicographically according to the specified sorting category.\n"
            + "Since tutee contains tutee-specific information such as grades and school, \n"
            + "Person who are not Tutees will be listed last "
            + "when such information is selected as the sorting category."
            + "Parameters: sort_category\n"
            + "Choice of sort_categories: "
            + CATEGORY_NAME + "\n"
            + CATEGORY_EDUCATION_LEVEL + "[Tutee specific]\n"
            + CATEGORY_GRADE + "[Tutee specific]\n"
            + CATEGORY_SCHOOL + "[Tutee specific]\n"
            + CATEGORY_SUBJECT + "[Tutee specific]\n"
            + "Example: " + COMMAND_WORD + " " + CATEGORY_GRADE;

    private final String category;
    private final Comparator<Person> comparator;

    public SortCommand(String category) {
        this.category = category;
        comparator = new PersonSortUtil().getComparator(category);
    }

    @Override
    public CommandResult execute() {
        model.sortFilteredPersonList(comparator);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortCommand // instanceof handles nulls
                && category.equals(((SortCommand) other).category));
    }
}
