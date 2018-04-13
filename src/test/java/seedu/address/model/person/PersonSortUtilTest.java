package seedu.address.model.person;

import static org.junit.Assert.assertEquals;
import static seedu.address.model.person.PersonSortUtil.CATEGORY_NAME;
import static seedu.address.model.person.PersonSortUtil.getComparator;

import java.util.Comparator;

import org.junit.Test;

import seedu.address.model.tutee.Tutee;
import seedu.address.testutil.TuteeBuilder;

//@@author yungyung04
public class PersonSortUtilTest {

    private static final String LOWEST_ORDER = "a";
    private static final String LOWER_ORDER = "b";
    private static final String HIGHER_ORDER = "c";
    private static final String HIGHEST_ORDER = "d";
    private static final String EDUCATION_LEVEL_PRIMARY = "primary";
    private static final String EDUCATION_LEVEL_SECONDARY = "secondary";
    private static final String EDUCATION_LEVEL_JUNIOR_COLLEGE = "junior college";

    private Person lowerOrder = new TuteeBuilder().withName(LOWER_ORDER).withEducationLevel(EDUCATION_LEVEL_PRIMARY)
            .withGrade(LOWER_ORDER).withSchool(LOWER_ORDER).withSubject(LOWER_ORDER).build();
    private Person higherOrder = new TuteeBuilder().withName(HIGHER_ORDER).withEducationLevel(EDUCATION_LEVEL_SECONDARY)
            .withGrade(HIGHER_ORDER).withSchool(HIGHER_ORDER).withSubject(HIGHER_ORDER).build();
    private Person versatileOrder;

    @Test
    public void getComparator_validNameCategory_compareSuccessfully() {
        //first person's name has lower lexicographical order and second person's other categories have higher order
        Comparator<Person> comparator = getComparator(CATEGORY_NAME);
        int expected = lowerOrder.getName().fullName.compareTo(higherOrder.getName().fullName);
        assertCompareSuccessfully(comparator, expected, lowerOrder, higherOrder);

        //first person's name has lower lexicographical order and second person's other categories have equal order
        versatileOrder = new TuteeBuilder((Tutee) lowerOrder).withName(HIGHER_ORDER).build();
        expected = lowerOrder.getName().fullName.compareTo(versatileOrder.getName().fullName);
        assertCompareSuccessfully(comparator, expected, lowerOrder, versatileOrder);

        //first person's name has lower lexicographical order and second person's other categories have lower order
        versatileOrder = new TuteeBuilder((Tutee) higherOrder).withEducationLevel(EDUCATION_LEVEL_JUNIOR_COLLEGE)
                .withGrade(LOWEST_ORDER).withSchool(LOWEST_ORDER).withSubject(LOWEST_ORDER).build();
        expected = lowerOrder.getName().fullName.compareTo(versatileOrder.getName().fullName);
        assertCompareSuccessfully(comparator, expected, lowerOrder, versatileOrder);

        //first person's name has equal lexicographical order
        expected = lowerOrder.getName().fullName.compareTo(lowerOrder.getName().fullName);
        assertCompareSuccessfully(comparator, expected, lowerOrder, lowerOrder);

        //first person's name has higher lexicographical order
        expected = higherOrder.getName().fullName.compareTo(lowerOrder.getName().fullName);
        assertCompareSuccessfully(comparator, expected, higherOrder, lowerOrder);
    }

    /**
     * Checks whether comparator is able to perform the desired comparison.
     */
    private void assertCompareSuccessfully(Comparator<Person> comparator, int expected, Person first, Person second) {
        int actual = comparator.compare(first, second);
        assertEquals(expected, actual);
    }
}
