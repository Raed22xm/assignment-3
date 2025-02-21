package dk.dtu.compute.course02324.assignment3.lists.uses;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class Person implements Comparable<Person> {

    final public String name;
    final public double weight;

    Person(@NotNull String name, @NotNull double weight) {
        if (name == null || weight <= 0) {
            throw new IllegalArgumentException("A person must be initialized with a non-null name and a weight greater than 0");
        }
        this.name = name;
        this.weight = weight;
    }

    @Override
    public int compareTo(@NotNull Person o) {
        if (o == null) {
            throw new IllegalArgumentException("Argument of compareTo() must not be null");
        }
        return Double.compare(this.weight, o.weight);
    }

    /**
     * Computes a simple string representation of this object.
     *
     * @return a string representation of this object
     */
    @Override
    public String toString() {
        return name + ", " + weight + "kg";
    }

    /**
     * Checks whether two Person objects are equal based on name and weight.
     *
     * @param o the object to be compared for equality
     * @return true if the given object represents an equivalent Person, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Double.compare(person.weight, weight) == 0 && Objects.equals(name, person.name);
    }

    /**
     * Returns a hash code value for the Person object.
     *
     * @return a hash code consistent with the equals() method
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, weight);
    }
}
