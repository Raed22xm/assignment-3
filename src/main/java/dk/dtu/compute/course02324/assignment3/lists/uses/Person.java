package dk.dtu.compute.course02324.assignment3.lists.uses;

import javax.validation.constraints.NotNull;

public class Person implements Comparable<Person> {

    final public String name;

    final public double weight;

    Person(@NotNull String name, @NotNull double weight) {
        if (name == null || weight <= 0) {
            throw new IllegalArgumentException("A persons must be initialized with a" +
                    "(non null) name and an age greater than 0");
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
        // This could be automatically generated, but this automatically
        // generated representation is a bit too verbose. Therefore, we
        // chose a simpler representation here.
        return name + ", " + weight + "kg";
    }

    /*
     * The following two methods must be implemented in order to respect the contract of objects
     * with respect to equals(), hashCode() and compareTo() methods. The details and reasons
     * as to why will be discussed much later.
     *
     * The implementations can be done fully automatically my IntelliJ (using the two attributes
     * of person).
     *
     * @param o
     * @return
     */
    /**
     * Checks if this object is equal to another object.
     *
     * @param o the object to compare with this instance
     * @return true if the objects are equal, false otherwise
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; //same memory reference and if it is the same object
        if (o == null || getClass() != o.getClass()) return false;//null or not the same class and if it is not the same class
        Person person = (Person) o;// safe cast
        return Double.compare(person.weight, weight) == 0 && name.equals(person.name);//check if name and weight is the same


    }
    /**
     * Computes the hash code for this object.
     *
     * @return a hash code value for this object
     */

    @Override
    public int hashCode() {
        int result = name.hashCode(); // start with name's hash code.
        long temp = Double.doubleToLongBits(weight); //convert weight to long to avoid overflow and convert wight to long bits
        result = 31 * result + (int) (temp ^ (temp >>> 32)); //combine name's hash code with weight's hash code
        return result;
    }


}