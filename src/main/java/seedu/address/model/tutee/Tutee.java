package seedu.address.model.tutee;

import java.util.Objects;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.UniqueTagList;

//@@author ChoChihTun
/**
 * Represents a Tutee in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Tutee extends Person {
    private Subject subject;
    private Grade grade;
    private EducationLevel educationLevel;
    private School school;

    /**
     * Every field must be present and not null.
     */
    public Tutee(Name name, Phone phone, Email email, Address address, Subject subject,
                 Grade grade, EducationLevel educationLevel, School school, Set<Tag> tags) {
        super(name, phone, email, address, tags);
        this.subject = subject;
        this.grade = grade;
        this.educationLevel = educationLevel;
        this.school = school;

        Tag tuteeTag = new Tag("Tutee");
        if (!this.tags.contains(tuteeTag)) {
            try {
                this.tags.add(tuteeTag);
            } catch (UniqueTagList.DuplicateTagException e) {
                // Should not have duplicate tag
                assert (false);
            }
        }
    }

    public Subject getSubject() {
        return subject;
    }

    public Grade getGrade() {
        return grade;
    }

    public EducationLevel getEducationLevel() {
        return educationLevel;
    }

    public School getSchool() {
        return school;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Tutee)) {
            return false;
        }

        Tutee otherPerson = (Tutee) other;
        return otherPerson.getName().equals(this.getName())
                && otherPerson.getPhone().equals(this.getPhone())
                && otherPerson.getEmail().equals(this.getEmail())
                && otherPerson.getAddress().equals(this.getAddress())
                && otherPerson.getEducationLevel().equals(this.getEducationLevel())
                && otherPerson.getGrade().equals(this.getGrade())
                && otherPerson.getSchool().equals(this.getSchool())
                && otherPerson.getSubject().equals(this.getSubject());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, subject, grade, educationLevel, school, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Phone: ")
                .append(getPhone())
                .append(" Email: ")
                .append(getEmail())
                .append(" Address: ")
                .append(getAddress())
                .append(" Subject: ")
                .append(getSubject())
                .append(" Grade ")
                .append(getGrade())
                .append(" Education Level: ")
                .append(getEducationLevel())
                .append(" School: ")
                .append(getSchool())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }

}
