package seedu.address.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import seedu.address.testutil.TaskBuilder;

public class TaskContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        TaskContainsKeywordsPredicate firstPredicate = new TaskContainsKeywordsPredicate(firstPredicateKeywordList);
        TaskContainsKeywordsPredicate secondPredicate = new TaskContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TaskContainsKeywordsPredicate firstPredicateCopy = new TaskContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));


        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_taskContainsKeywords_returnsTrue() {
        // One keyword
        TaskContainsKeywordsPredicate predicate = new TaskContainsKeywordsPredicate(Collections
                .singletonList("exampleTask1"));
        assertTrue(predicate.test(new TaskBuilder().withDescription("exampleTask1").build()));

        // Multiple keywords
        predicate = new TaskContainsKeywordsPredicate(Arrays.asList("exampleTask1", "exampleTask2"));
        assertTrue(predicate.test(new TaskBuilder().withDescription("exampleTask1 exampleTask2").build()));

        // Only one matching keyword
        predicate = new TaskContainsKeywordsPredicate(Arrays.asList("exampleTask2", "exampleTask3"));
        assertTrue(predicate.test(new TaskBuilder().withDescription("exampleTask1 exampleTask3").build()));

        // Mixed-case keywords
        predicate = new TaskContainsKeywordsPredicate(Arrays.asList("eXampleTask1", "ExampleTask2"));
        assertTrue(predicate.test(new TaskBuilder().withDescription("exampleTask1 exampleTask2").build()));
    }

    @Test
    public void test_taskDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        TaskContainsKeywordsPredicate predicate = new TaskContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new TaskBuilder().withDescription("exampleTask1").build()));

        // Non-matching keyword
        predicate = new TaskContainsKeywordsPredicate(Arrays.asList("exampleTask3"));
        assertFalse(predicate.test(new TaskBuilder().withDescription("exampleTask1 exampleTask2").build()));

        // Keywords match duration and dateandtime, but does not match description
        predicate = new TaskContainsKeywordsPredicate(Arrays.asList("exampleTask1", "03:20", "02-05-2018 23:20"));
        assertFalse(predicate.test(new TaskBuilder().withDescription("exampleTask3").withDuration("03:20")
                .withDateAndTime("02-05-2018 23:20").build()));

        // Keywords match description and duration, but does not match name
        predicate = new TaskContainsKeywordsPredicate(Arrays.asList("exampleTask1", "03:20", "02-05-2018 03:20"));
        assertFalse(predicate.test(new TaskBuilder().withDescription("exampleTask2").withDuration("03:20")
                .withDateAndTime("02-05-2018 03:20").build()));

    }
}

